package com.doctor.cattle.customerservice.service.farm_user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.cattle.customerservice.adapter.farm_user_adapter.FarmUserAdapter;
import com.doctor.cattle.customerservice.adapter.user_adapter.UserAdapter;
import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.dto.user.UserDTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;
import com.doctor.cattle.customerservice.exception.farm_user.FarmUserRegistrationException;
import com.doctor.cattle.customerservice.exception.user.UserNotFoundException;
import com.doctor.cattle.customerservice.repository.FarmRepository;
import com.doctor.cattle.customerservice.repository.FarmUserRepository;
import com.doctor.cattle.customerservice.service.user.UserService;

@Service
public class FarmUserServiceImpl implements FarmUserService {

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private FarmUserRepository farmUserRepository;

	@Autowired
	private UserService userService;

	@Override
	public Farm_User_DTO registerUser(Farm_User_DTO user) throws FarmUserRegistrationException {
		try {
			if (validFarmUser(user)) {
				FarmUserAdapter adapter = new FarmUserAdapter();
				FarmUser farmUser = adapter.getFarmUser(user);
				if (user.getFarm() != null) {
					if (user.getFarm().getId() == null) {
						throw new FarmUserRegistrationException("Farm id  can't be null");
					}
					Optional<Farm> farm = farmRepository.findById(user.getFarm().getId());

					if (!farm.isPresent()) {
						throw new FarmUserRegistrationException(
								"Farm with Id : " + user.getFarm().getId() + " can't be found");
					}
					farmUser.setFarm(farm.get());

				}

				farmUser = farmUserRepository.save(farmUser);
				return adapter.getFarmUserDTO(farmUser);
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public void invertAccess(Farm_User_DTO user) throws UserNotFoundException {
		// TODO Auto-generated method stub
		if (null == user.getId()) {
			throw new UserNotFoundException("Unable to found user with user Id null");
		}

		Optional<FarmUser> farmUser = farmUserRepository.findById(user.getId());

		if (!farmUser.isPresent()) {
			throw new UserNotFoundException("Unable to found user with user Id : " + user.getId());
		}

		UserDTO userInSession = userService.getUserInSession();

		if (userInSession.getIsOwner() == false) {
			throw new UserNotFoundException("Only owners are allowed to reset farm users access");
		}

		if (!farmUser.get().getFarm().getOwner().getUserName().equals(userInSession.getUserName())) {

			throw new UserNotFoundException(
					"Owner : " + userInSession.getUserName() + " is not allowed to change access of farm user : "
							+ farmUser.get().getUserName() + " of farm : " + farmUser.get().getFarm().getName());
		}

		Access userAccess = farmUser.get().getaccessGrantedd();
		farmUser.get().setaccessGrantedd(userAccess == Access.GRANTED ? Access.DENIED : Access.GRANTED);

		farmUserRepository.save(farmUser.get());
	}

	private boolean validFarmUser(Farm_User_DTO user) throws FarmUserRegistrationException {

		if (user == null) {
			throw new FarmUserRegistrationException("Farm user object can't be null");
		}

		if (user.getUserName() == null || user.getUserName().trim().equals("")) {
			throw new FarmUserRegistrationException("Farm's user name  can't be null or empty");
		}

		if (user.getPassword() == null || user.getPassword().trim().equals("")) {
			throw new FarmUserRegistrationException("Farm's user password  can't be null or empty");
		}

		if (userService.userExists(user.getUserName())) {
			throw new FarmUserRegistrationException("User name  already exists");

		}
		return true;
	}

}
