package com.fuji.erms.service;

import java.util.List;
import java.util.Optional;

import com.fuji.erms.dto.RequestTransactionDto;
import com.fuji.erms.exceptions.RequestTransactionException;
import com.fuji.erms.model.RequestTransaction;

public interface RequestTransactionService {

	public RequestTransaction createRequest(
			RequestTransaction requesttransaction)
			throws RequestTransactionException;

	public List<RequestTransaction> getAll() throws RequestTransactionException;

	public Optional<RequestTransaction> getRequestById(Integer requestId)
			throws RequestTransactionException;

	public void deleteRequestById(Integer requestId)
			throws RequestTransactionException;

	public RequestTransaction updateStatus(
			RequestTransaction requestTransaction)
			throws RequestTransactionException;

	public List<RequestTransactionDto> filterRequests(Integer requestId,
			String requestType, String requestStatus, Integer employeeId,
			String employeeName, String createDateTime,
			String remark, String requestBy);

}
