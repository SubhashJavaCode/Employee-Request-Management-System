package com.fuji.erms.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fuji.erms.constants.RequestManagementConstants;
import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.RequestTransactionException;
import com.fuji.erms.model.RequestTransaction;
import com.fuji.erms.service.RequestTransactionService;

/**
 * @author HrushalG This class is used for to controls the flow of the data. It
 *         contains getAll,update,update user,get request id,delete request
 *         id,getRequestByRequestType,getRequestByStatus,getRequestByEmployeeId,
 *         getRequestByCreatedDate,getRequestByemployeeName,save and
 *         updateStatus methods.
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/rmc")

public class RequestTransactionController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RequestTransactionController.class);
	@Autowired
	private RequestTransactionService requestTransactionService;

	ModelMapper modelMapper = new ModelMapper();

	/**
	 * This method is used for fetch all the data from request transaction
	 * table.
	 * 
	 * @author HrushalG
	 * @return
	 * @throws Exception
	 * 
	 */

	@GetMapping("/getAll")
	public ResponseEntity<List<RequestTransaction>> getAll() {
		try {

			LOGGER.info(
					"Request Transaction Rest controller Implementation-getAll method:Start()");
			List<RequestTransaction> requestlist = requestTransactionService
					.getAll();

			if (!requestlist.isEmpty()) {

				LOGGER.info(
						"Request Transaction Rest controller Implementation-getAll method:End()");
				return new ResponseEntity<>(requestlist, HttpStatus.OK);
			} else {
				LOGGER.error(
						"Request Transaction Rest controller Implementation- getAll method EMP list is null:End()");
				return new ResponseEntity<>(requestlist, HttpStatus.OK);
			}
		} catch (Exception e) {
			LOGGER.error("Request Transaction get all error occured {0}", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * This method is used for update the Update Date Time and User
	 * 
	 * @author HrushalG
	 * @param requestTransaction
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 */
	@PutMapping("/updateUser")
	public ResponseEntity<RequestTransaction> updateUser(
			@RequestBody RequestTransactionDto requestTransactionDto,
			HttpServletRequest request) throws RequestTransactionException {
		RequestTransaction requestTransaction = modelMapper
				.map(requestTransactionDto, RequestTransaction.class);
		try {
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- updateUser method:Start()");
			Optional<RequestTransaction> optionalRequest = requestTransactionService
					.getRequestById(requestTransaction.getRequestId());

			if (!optionalRequest.isEmpty()) {

				RequestTransaction rmc = optionalRequest.get();
				if (rmc != null) {
					rmc.setUpdateDateTime(
							requestTransaction.getUpdateDateTime());
					rmc.setUpdateUser(requestTransaction.getUpdateUser());

					RequestTransaction updatedRequestTransaction = requestTransactionService
							.updateStatus(rmc);
					LOGGER.info(
							"RequestTransaction Rest controller Implementation- updateUser method:End()");
					return new ResponseEntity<>(updatedRequestTransaction,
							HttpStatus.OK);
				} else {
					LOGGER.info(
							"RequestTransaction Rest controller Implementation- updateUser method - No content is present:End()");
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} else {
				LOGGER.info("RequestTransaction updateUser error occured");
				LOGGER.info(
						"RequestTransaction Rest controller Implementation- updateUser method - Optional Request is null");
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (RequestTransactionException e) {

			throw new RequestTransactionException("Exception" + e);

		}
	}

	/**
	 * This method is used for fetch the request by using requestId
	 * 
	 * @author HrushalG
	 * @param requestId
	 * @return
	 * 
	 */
	@GetMapping("/get/{requestId}")
	public ResponseEntity<Optional<RequestTransaction>> getRequestById(
			@PathVariable("requestId") Integer requestId) {
		try {
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- getRequestById() method:Start()");
			Optional<RequestTransaction> requestTransaction = requestTransactionService
					.getRequestById(requestId);
			if (!requestTransaction.isEmpty()) {

				LOGGER.info("RequestTransaction getRequestById() method:End()");
				return ResponseEntity.ok().body(requestTransaction);
			} else {
				LOGGER.error("RequestTransaction get RequestID error occured");
				LOGGER.info(
						"RequestTransaction Rest controller Implementation- getRequestById() method:End()");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {

			LOGGER.info("RequestTransaction get request id error occured %s",
					e);
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- getRequestById() method:End()");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * This method is used for delete request by using request Id
	 * 
	 * @author HrushalG
	 * @param requestId
	 * @return
	 * @throws Exception
	 * 
	 */
	@DeleteMapping("/delete/{requestid}")
	public Map<String, String> deleteRequestById(
			@PathVariable("requestid") Integer requestId)
			throws RequestTransactionException {

		try {
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- deleteRequestById() method:Start()");
			Optional<RequestTransaction> requesttransaction = requestTransactionService
					.getRequestById(requestId);

			if (!requesttransaction.isEmpty()) {
				requestTransactionService.deleteRequestById(requestId);
				LOGGER.info(
						"RequestTransaction deleteRequestById() method:End()");
				return Collections.singletonMap("success",
						"Record deleted Successfully");

			} else {
				LOGGER.info(
						"RequestTransaction Rest controller Implementation- deleteRequestById() method:End()");
				return Collections.singletonMap("Failed",
						"Record failed to delete. error occurs");
			}
		} catch (RequestTransactionException e) {
			LOGGER.info("RequestTransaction delete request id error occured:%s",
					e);

			return Collections.singletonMap("Failed", "INTERNAL_SERVER_ERROR");
		}
	}

	/**
	 * 
	 * @author Subhash
	 * @param requestTransaction
	 * @param request
	 * @return
	 * @throws Exception
	 *             This method is used for save the request
	 */
	@PostMapping("/save")
	public ResponseEntity<Object> createRequest(
			@RequestParam("createUser") Integer createUser,
			@RequestBody RequestTransactionDto requestTransactionDto,
			HttpServletRequest request) throws RequestTransactionException {
		RequestTransaction requestTransaction = modelMapper
				.map(requestTransactionDto, RequestTransaction.class);
		requestTransaction.setCreateUser(createUser);
		requestTransaction.setRequestStatus(RequestManagementConstants.PENDING);
		try {
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- creatRequest() method:Start()");
			RequestTransaction createdRequest = this.requestTransactionService
					.createRequest(requestTransaction);
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- creatRequest() method:End()");
			return ResponseEntity.ok().body(createdRequest);

		} catch (RequestTransactionException e) {
			LOGGER.info(
					"RequestTransaction Rest controller Implementation- creatRequest() Exception: %s ",
					e);
			return ResponseEntity.ok().body(HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * @author HrushalG
	 * @param requestId
	 * @param requestType
	 * @param requestStatus
	 * @param employeeId
	 * @param employeeName
	 * @param createUser
	 * @param createDateTime
	 * @param remark
	 * @return List<RequestTransaction>>
	 * @throws RequestTransactionException
	 *             This method is used to filter requests in table DASHBOARD PM
	 *             AND LM.
	 * @throws ParseException
	 */
	@GetMapping("/filterRequests")
	public ResponseEntity<List<RequestTransactionDto>> filterRequests(
			@RequestParam(required = false) Integer requestId,
			@RequestParam(required = false) String requestType,
			@RequestParam(required = false) String requestStatus,
			@RequestParam(required = false) Integer employeeId,
			@RequestParam(required = false) String employeeName,

			@RequestParam(required = false) String createDateTime,
			@RequestParam(required = false) String remark,
			@RequestParam(required = false) String requestBy

	) throws RequestTransactionException {
		List<RequestTransactionDto> list = requestTransactionService
				.filterRequests(requestId, requestType, requestStatus,
						employeeId, employeeName, createDateTime, remark,
						requestBy);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
