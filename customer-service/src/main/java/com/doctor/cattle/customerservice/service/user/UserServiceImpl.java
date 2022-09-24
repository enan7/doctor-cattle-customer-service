package com.doctor.cattle.customerservice.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctor.cattle.customerservice.adapter.user_adapter.UserAdapter;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.dto.user.UserDTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;
import com.doctor.cattle.customerservice.repository.CompanyOwnerRepository;
import com.doctor.cattle.customerservice.repository.FarmUserRepository;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private CompanyOwnerRepository companyOwnerRepository;

	@Autowired
	private FarmUserRepository farmUserRepository;

	@Override
	public UserDTO findByUserName(String userName) {
		// TODO Auto-generated method stub
		UserDTO user = getCompanyOwner(userName);
		if (null == user) {
			user = getFarmUser(userName);
		}
		return user;
	}

	@Override
	public UserDTO login(String userName, String password) throws AccessDeniedException {

		UserAdapter adapter = new UserAdapter();
		CompanyOwner owner = companyOwnerRepository.findByUserName(userName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean passwordMatched = false;
		try {
		if (null == owner) {
			FarmUser user = farmUserRepository.findByUserName(userName);
			if (null == user) {
				return null;
			}
			passwordMatched = encoder.matches(password, user.getPassword());
			if (!passwordMatched) {
				return null;
			}
			if (checkAccesAllowed(userName)) {
				return adapter.getUserDTO(user);
			}
		}
		passwordMatched = encoder.matches(password, owner.getPassword());
		if (!passwordMatched) {
			return null;
		}
		if (checkAccesAllowed(userName)) {
			return adapter.getUserDTO(owner);
		}
		}catch (Exception e) {
			throw e;
		}
		
		return null;
	}

	@Override
	public Boolean userExists(String userName) {

		if (null == userName) {
			return false;
		}

		Long count = companyOwnerRepository.countByUserName(userName);

		if (count == 0) {
			count = farmUserRepository.countByUserName(userName);
		}

		return count > 0;
	}

	@Override
	public UserDTO finById(Long id) {
		Optional<CompanyOwner> owner = companyOwnerRepository.findById(id);
		UserAdapter adapter = new UserAdapter();
		if (owner.isPresent()) {
			return adapter.getUserDTO(owner.get());
		}

		Optional<FarmUser> farmUser = farmUserRepository.findById(id);
		if (farmUser.isPresent()) {
			return adapter.getUserDTO(farmUser.get());
		}
		return null;
	}

	@Override
	public boolean checkAccesAllowed(String userName) throws AccessDeniedException {
		// TODO Auto-generated method stub
		UserDTO dto = findByUserName(userName);

		if (null != dto) {
			if (dto.getAccessGranted() != Access.GRANTED) {
				throw new AccessDeniedException("No Access Allowed to user : " + userName);
			} else if (dto.getCompany().getOwner().getAccessGranted() != Access.GRANTED) {
				throw new AccessDeniedException("No Access Allowed to farm owner : " + dto.getCompany().getOwner().getUserName());
			}
			
			if (null != dto.getFarms() && dto.getIsOwner() == false) {
				for (Farm_DTO farm : dto.getFarms()) {
					if (farm.getAccessGranted() != Access.GRANTED) {
						throw new AccessDeniedException("No Access Allowed to farm : " + farm.getName());
					}

				}

			}
			if (dto.getCompany().getAccessGranted() != Access.GRANTED) {
				throw new AccessDeniedException("No Access Allowed to company : " + dto.getCompany().getCompanyName());

			}

			return true;
		}

		return false;
	}

	private UserDTO getCompanyOwner(String userName) {
		CompanyOwner owner = companyOwnerRepository.findByUserName(userName);
		UserAdapter adapter = new UserAdapter();

		if (null != owner) {
			return adapter.getUserDTO(owner);
		}
		return null;
	}

	private UserDTO getFarmUser(String userName) {
		FarmUser user = farmUserRepository.findByUserName(userName);
		UserAdapter adapter = new UserAdapter();

		if (null != user) {
			return adapter.getUserDTO(user);
		}
		return null;
	}

	@Override
	public UserDTO getUserInSession() {
		Authentication authentication =  (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if (null != authentication) {
		return findByUserName(authentication.getName());
		}
		return null;
	}

}
