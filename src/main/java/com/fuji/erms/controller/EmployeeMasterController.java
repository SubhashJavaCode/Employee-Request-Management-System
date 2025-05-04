package com.fuji.erms.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fuji.erms.constants.RequestManagementConstants;
import com.fuji.erms.dto.EmployeeManagerDetailsDto;
import com.fuji.erms.dto.ManagerDto;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.EmployeeMasterException;
import com.fuji.erms.model.EmployeeMaster;
import com.fuji.erms.model.RequestTransaction;
import com.fuji.erms.service.EmployeeMasterService;
import com.fuji.erms.service.RequestTransactionService;
import com.fuji.erms.service.UserMasterService;
import com.fuji.erms.util.EmployeeMasterUtil;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/emc")
/**
 * This Class is used for to control the flow of employee master data. It
 * contains
 * saveEmployee,getAll,updateEmployee,getEmployeeById,getEmployeeByEmployeeName,
 * delete employee id,updateEmployeeforManagerChange,
 * 
 * @author Subhash
 */
public class EmployeeMasterController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeMasterController.class);

	@Autowired
	EmployeeMasterService employeeMasterService;

	@Autowired
	RequestTransactionService requestTransactionService;

	@Autowired
	UserMasterService userMasterService;

	ModelMapper modelMapper = new ModelMapper();

	/**
	 * This method is used to fetch all the data from employee master table.
	 * 
	 * @author Subhash
	 * @return
	 */
	@GetMapping("/getAll")
	public ResponseEntity<List<EmployeeMaster>> getAll() {
		try {
			LOGGER.info(
					"EmployeeMaster Rest controller Implementation-getAll method:Start()");
			List<EmployeeMaster> emplist = employeeMasterService.getAll();

			if (!emplist.isEmpty()) {
				LOGGER.info(
						"EmployeeMaster Rest controller Implementation-getAll method:End()");
				return new ResponseEntity<>(emplist, HttpStatus.OK);
			} else {
				LOGGER.error(
						"EmployeeMaster Rest controller Implementation- getAll method EMP list is null:End()");
				return new ResponseEntity<>(emplist, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			LOGGER.error("Emp master get all error occured %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is used for delete the employee request by employee Id
	 * 
	 * @author Subhash
	 * @param employeeId
	 * @return
	 * @throws Exception
	 * 
	 */
	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployeeByEmployeeId(
			@PathVariable("employeeId") Integer employeeId)
			throws EmployeeMasterException {

		try {
			LOGGER.info(
					"EmployeeMaster Rest controller Implementation- deleteEmployeeByEmployeeId() method:Start()");
			Optional<EmployeeMaster> requesttransaction = employeeMasterService
					.getEmployeeById(employeeId);
			if (!requesttransaction.isEmpty()) {
				employeeMasterService.deleteEmployeeByEmployeeId(employeeId);
				LOGGER.info(
						"EmployeeMaster  deleteEmployeeByEmployeeId() method:End()");
				return new ResponseEntity<>("Record deleted Successfully",
						HttpStatus.OK);
			} else {
				LOGGER.info(
						"EmployeeMaster  deleteEmployeeByEmployeeId() method:End()");
				return new ResponseEntity<>(
						"Record failed to delete. error occurs",
						HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			LOGGER.info("RequestTransaction delete request id error occured %s",
					e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @author Subhash
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/getEmployeeById")
	public ResponseEntity<EmployeeManagerDetailsDto> getEmployeeById(
			@RequestParam("employeeId") String employeeId) {
		try {
			LOGGER.info("Employee master getEmployeeById:Start()");

			Optional<EmployeeMaster> emcEmployeeOptional = employeeMasterService
					.getEmployeeById(Integer.parseInt(employeeId));
			if (!emcEmployeeOptional.isEmpty()) {
				EmployeeMaster emcEmployee = emcEmployeeOptional.get();
				Optional<EmployeeMaster> emcManagerOptional = employeeMasterService
						.getEmployeeById(emcEmployee.getManagerId());
				if (!emcManagerOptional.isEmpty()) {

					EmployeeMaster emcManager = emcManagerOptional.get();

					EmployeeManagerDetailsDto employeeManagerDetailsDto = new EmployeeManagerDetailsDto(
							emcEmployee.getEmployeeName(),
							emcManager.getManagerId(),
							EmployeeMasterUtil.changeNameFjLevel(
									emcEmployee.getFjLevel()),
							EmployeeMasterUtil.changeNameJlptLevel(
									emcEmployee.getJlptLevel()),
							emcManager.getEmployeeName());

					LOGGER.info("Employee master getEmployeeById:End()");
					return new ResponseEntity<>(employeeManagerDetailsDto,
							HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);

				}
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}

		} catch (Exception e) {
			LOGGER.error("Emp master getEmployeeById error occured %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @author Subhash
	 * @param requestId
	 * @return EmployeeMaster
	 * 
	 *         This method is used to update status to "approve" and update
	 *         employee information.
	 */
	@PutMapping("/approve")
	public ResponseEntity<EmployeeMaster> updateEmployeeInfo(
			@RequestBody RequestTransactionDto requestTransactionDto,
			@RequestParam("requestId") Integer requestId) {
		RequestTransaction requestTransaction = modelMapper
				.map(requestTransactionDto, RequestTransaction.class);

		try {
			LOGGER.info(
					"EmployeeMaster Rest controller Implementation- updateEmployeeInfo method:Start()");

			Optional<RequestTransaction> request = requestTransactionService
					.getRequestById(requestId);

			if (request.isPresent()) {
				RequestTransaction requestobj = request.get();

				requestobj.setRequestStatus(RequestManagementConstants.APPROVE);
				requestobj.setUpdateUser(requestTransaction.getUpdateUser());
				requestobj.setUpdateDateTime(
						requestTransaction.getUpdateDateTime());
				requestTransactionService.updateStatus(requestobj);

				Optional<EmployeeMaster> updatedEmp = employeeMasterService
						.getEmployeeById(requestobj.getEmployeeId());
				if (!updatedEmp.isEmpty()) {
					EmployeeMaster emc = updatedEmp.get();

					if (requestobj.getRequestStatus()
							.equals(RequestManagementConstants.APPROVE)) {

						if (requestobj.getRequestType().equals(
								RequestManagementConstants.MANAGER_CHANGE)) {

							Optional<EmployeeMaster> foundemp = employeeMasterService
									.getEmployeeById(Integer
											.valueOf(requestobj.getRemark()));
							if (!foundemp.isEmpty()) {
								emc.setManagerId(Integer
										.valueOf(requestobj.getRemark()));
							} else {
								LOGGER.info(
										"EmployeeMaster Rest controller Implementation- updateEmployeeInfo method:No Manager is present with this id..");
								return new ResponseEntity<>(
										HttpStatus.NO_CONTENT);
							}

						} else if (requestobj.getRequestType().equals(
								RequestManagementConstants.FJ_LEVEL_CHANGE)) {
							emc.setFjLevel(requestobj.getRemark());
						} else {
							emc.setJlptLevel(requestobj.getRemark());
						}

						EmployeeMaster updatedEmployeeMaster = employeeMasterService
								.updateEmployee(emc);

						LOGGER.info(
								"EmployeeMaster Rest controller Implementation- updateEmployeeInfo method: End()");
						return new ResponseEntity<>(updatedEmployeeMaster,
								HttpStatus.OK);

					} else {
						LOGGER.error(
								"EmployeeMaster Rest controller Implementation- updateEmployeeInfo method:End()");

						return new ResponseEntity<>(
								HttpStatus.NON_AUTHORITATIVE_INFORMATION);

					}

				} else {
					LOGGER.error(
							"EmployeeMaster updateEmployeeInfo  error occured:End()");
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} else {
				LOGGER.error("Emp master updateEmployeeInfo error occured:");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			}
		} catch (Exception e) {
			LOGGER.error("Emp master updateEmployeeInfo error occured:%s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @author Subhash
	 * @param requestId
	 * @return RequestTransaction
	 * 
	 *         This method is used to update status of request to "reject"
	 */
	@PutMapping("/reject")
	public ResponseEntity<RequestTransaction> updateEmployeeStatusAsReject(
			@RequestBody RequestTransactionDto requestTransactionDto,
			@RequestParam("requestId") Integer requestId) {
		RequestTransaction requestTransaction = modelMapper
				.map(requestTransactionDto, RequestTransaction.class);

		try {
			LOGGER.info(
					"EmployeeMaster Rest controller Implementation- updateEmployeeStatusAsReject method:Start()");

			Optional<RequestTransaction> request = requestTransactionService
					.getRequestById(requestId);
			if (!request.isEmpty()) {
				RequestTransaction requestobj = request.get();
				requestobj.setRequestStatus(RequestManagementConstants.REJECT);
				requestobj.setUpdateUser(requestTransaction.getUpdateUser());
				requestobj.setUpdateDateTime(
						requestTransaction.getUpdateDateTime());
				RequestTransaction reqtr = requestTransactionService
						.updateStatus(requestobj);

				LOGGER.info(
						"EmployeeMaster Rest controller Implementation- updateEmployeeInfo method:End()");
				return new ResponseEntity<>(reqtr, HttpStatus.OK);
			} else {

				LOGGER.error("Emp master updateEmployeeInfo request is null");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			LOGGER.error("Emp master updateEmployeeInfo error occured: %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @author Subhash
	 * @return List<UserMaster>
	 * 
	 *         This method is used to return all managers list.
	 */
	@GetMapping("/getAllManagers")
	public ResponseEntity<List<ManagerDto>> getAllManagers() {

		try {
			LOGGER.info(
					"EmployeeMaster Rest controller Implementation-getAllManagers() method:Start()");
			List<EmployeeMaster> mnglist = employeeMasterService.getManagers();

			List<ManagerDto> mngDtolist = mnglist.stream()
					.map(element -> modelMapper.map(element, ManagerDto.class))
					.collect(Collectors.toList());
			if (!mnglist.isEmpty()) {
				LOGGER.info(
						"EmployeeMaster Rest controller Implementation-getAllManagers() method:End()");
				return new ResponseEntity<>(mngDtolist, HttpStatus.OK);
			} else {
				LOGGER.error(
						"EmployeeMaster Rest controller Implementation- getAllManagers() method Managers list is null:End()");
				return new ResponseEntity<>(mngDtolist, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			LOGGER.error("Emp master getAllManagers() error occured %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
