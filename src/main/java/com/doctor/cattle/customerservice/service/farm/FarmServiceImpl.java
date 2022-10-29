package com.doctor.cattle.customerservice.service.farm;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.cattle.customerservice.adapter.farm_adapter.FarmAdapter;
import com.doctor.cattle.customerservice.controller.response.livestock.GetLiveStockResponse;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.dto.farm.livestock.LiveStockDTO;
import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.role.Role;
import com.doctor.cattle.customerservice.exception.farm.FarmNotFoundException;
import com.doctor.cattle.customerservice.exception.farm.FarmRegistrationException;
import com.doctor.cattle.customerservice.repository.CompanyOwnerRepository;
import com.doctor.cattle.customerservice.repository.FarmRepository;
import com.doctor.cattle.customerservice.service.RestTemplateService;
import com.doctor.cattle.customerservice.service.user.UserService;

@Service
public class FarmServiceImpl extends RestTemplateService implements FarmService {

	@Autowired
	private FarmRepository farmRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyOwnerRepository companyOwnerRepository;

	@Override
	public Farm_DTO addFarm(Farm_DTO farmDTO) throws FarmRegistrationException {
		try {
			if (validFarm(farmDTO)) {
				FarmAdapter adapter = new FarmAdapter();
				Farm farm = adapter.getFarm(farmDTO);
				CompanyOwner owner = getCompnayOwner(farmDTO.getOwner().getId());
				if (owner == null) {
					throw new FarmRegistrationException("Unable to find owener");
				} else if (owner.getRole() != Role.OWNER) {
					throw new FarmRegistrationException("Only farm owners are allowed to register a farm");
				}
				if (farmAlreadyExists(owner, farmDTO.getName())) {
					throw new FarmRegistrationException(
							"Farm with name " + farmDTO.getName() + " already registered against this ower");
				}
				farm.setOwner(owner);
				farm = farmRepository.save(farm);
				if (null != farm) {
					return adapter.getFarmDTO(farm);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public boolean isFarmExists(Long farmId) {
		return farmRepository.countById(farmId) > 0;
	}

	@Override
	public GetLiveStockResponse getLiveStock(Long farmId) throws FarmNotFoundException {
		if (isFarmExists(farmId)) {
			GetLiveStockResponse response = getForObject("CATTLE_SERVICE", "/api/cattle-service/get-live-stock/"+farmId,
					GetLiveStockResponse.class);
			return response;
		}
		throw new FarmNotFoundException("Unable to found farm with id : "+farmId);
	}

	private boolean validFarm(Farm_DTO farmDTO) throws FarmRegistrationException {

		AtomicReference<String> message = new AtomicReference<>("");
		AtomicBoolean isValid = new AtomicBoolean(true);

		if (null == farmDTO.getOwner()) {
			isValid.set(false);
			message.set("Owner is Required while adding a farm");
		} else if (null == farmDTO.getOwner().getId()) {
			isValid.set(false);
			message.set("Owner Id is missing while adding the farm");
		} else if (null == farmDTO.getName() || farmDTO.getName().trim().equals("")) {
			isValid.set(false);
			message.set("Farm Name can't be empty");
		} else if (null != farmDTO.getUsers()) {
			for (Farm_User_DTO user : farmDTO.getUsers()) {
				if (user.getUserName() == null || user.getUserName().trim().equals("")) {
					isValid.set(false);
					message.set("User is Required while adding a farm");
					break;
				} else if (userService.userExists(user.getUserName())) {
					isValid.set(false);
					message.set("UserName " + user.getUserName() + "already exists");
					break;
				}
			}
		}

		if (isValid.get() == false) {
			throw new FarmRegistrationException(message.get());
		}

		return true;
	}

	private CompanyOwner getCompnayOwner(Long id) {
		Optional<CompanyOwner> owner = companyOwnerRepository.findById(id);
		return owner.get();
	}

	private boolean farmAlreadyExists(CompanyOwner owner, String farmName) throws FarmRegistrationException {
		if (owner == null) {
			throw new FarmRegistrationException("Owner can't be null");
		}
		if (owner.getFarms() == null) {
			return false;
		} else if (owner.getFarms().size() == 0) {
			return false;
		}

		Optional<Farm> farmToFound = owner.getFarms().stream().filter(farm -> farm.getName().equals(farmName))
				.findAny();

		return farmToFound.isPresent();
	}

}
