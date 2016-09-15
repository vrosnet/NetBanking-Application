package com.i2i.netbankingApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.i2i.netbankingApplication.exception.DataBaseException;
import com.i2i.netbankingApplication.service.TransactionService;

/**
 * <p>
 *     When request comes from JSP page. 
 *     TransactionController performs add new transaction or view all notifications or view all transactions or transaction 
 *     permission or transaction ignore with model(transaction), service(transaction) and return the responses to JSP page.
 *     It handles the NumberFormatException, DataBaseException.
 *     If exception occurs it will write what type of exception occurred.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-03
 */
@Controller
public class TransactionController {
	private TransactionService transactionService = new TransactionService();
        
        	
	/**
     * Return the JSP page that contains options for transaction operation
     * 
     * @return transactionIndex
     *     Return to the transactionIndex JSP page.
     */
	@RequestMapping(value = "/TransactionIndex")
	public String customer() {
		return "TransactionIndex";
	}
        
    /**
	 * <p>
	 *     It is return to the AddTransaction JSP page.
	 *     This form used to user transaction.
	 * </p>
	 * 
	 * @return AddTransaction
	 *     Return to the AddTransaction JSP page.
	 */ 
	@RequestMapping(value = "/addTransaction")
	public String transactionOpration() {
		return "AddTransaction";
	}
        
    /**
	 * <p>
     *     Get the transaction detail from JSP page and pass to getTransactionDetail method in transactionService.
     *     Return to JSP page transactionRegistration with status message(success or failure) Or Address object.
     * </p>
     * 
	 * @param debitAccountNumber
	 *     debitAccountNumber of Account.
	 *     
	 * @param creditAccountNumber
	 *     creditAccountNumber of Account.
	 *     
	 * @param ifscode
	 *     ifsCode of Branch.
	 *     
	 * @param amount
	 *     amount of transaction.
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return TransactionIndex
     *     Return to JSP page TransactionIndex with status message(success or failure).
     */
	@RequestMapping(value = "/insertTransaction", method = RequestMethod.POST)
	public String insertransaction(@RequestParam("customerId") String customerId,
			@RequestParam("creditAccountNumber") String creditAccountNumber, @RequestParam("ifscode") String ifscode,
			@RequestParam("amount") String amount, ModelMap message) {
		try {
			message.addAttribute("message", transactionService.getTransactionDetail(customerId ,
					creditAccountNumber, ifscode, Double.parseDouble(amount)));
		} catch (DataBaseException e) {
			message.addAttribute("message", e.toString());
		} finally {
			return "UserHomePage";
		}
	}

   /**
	 * <p>
	 *     This Method call to getAllNotification method in transactionService.
     *     Return to the RetrieveAllNotification JSP page with notification lists or status message(failure).
     * </p>
     * 
	 * @return RetrieveAllNotification
	 *     Return to the RetrieveAllNotification JSP page with notification lists or status message(failure).
	 */
	@RequestMapping(value = "/notification", method = RequestMethod.GET)
	public ModelAndView notification() {
		try {
			return new ModelAndView("RetrieveAllNotification", "notifications", transactionService.getAllNotification());
		} catch (DataBaseException e) {
			return new ModelAndView("RetrieveAllNotification", "message", e.getMessage().toString());
		}
	}

   /**
	 * <p>
	 *     This Method call to getAllTransaction method in transactionService.
     *     Return to the RetrieveAllTransaction JSP page with transaction lists or status message(failure).
     * </p>
	 *     
	 * @return RetrieveAllTransaction
	 *     Return to the RetrieveAllTransaction JSP page with transaction lists or status message(failure).
	 */
	@RequestMapping(value = "/viewAllTransaction", method = RequestMethod.GET)
	public ModelAndView viewAllTransaction() throws DataBaseException {
		try {
			return new ModelAndView("RetrieveAllTransaction", "transactions", transactionService.getAllTransaction());
		} catch (DataBaseException e) {
			return new ModelAndView("RetrieveAllTransaction", "message", e.getMessage().toString());
		}
	}
        
    
	/**
	 * <p>
	 *     This Method call to getAccountByCustomerId method in transactionService.
     *     Return to the RetrieveCustomerAccount JSP page with customer account or status message(failure).
     * </p>
     * 
	 * @param customerId
	 *     Id of Customer entered by user to view the corresponding record(Account Detail).
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return RetrieveCustomerAccount
	 *     Return to the RetrieveCustomerAccount JSP page with customer account detail or status message(failure).
	 */
	@RequestMapping(value = "/getAccountByCustomerId", method = RequestMethod.GET)
	public ModelAndView getAccountByCustomerId(@RequestParam("customerId") String customerId, ModelMap message) {
		try {
			return new ModelAndView("RetrieveCustomerAccount", "accountDetail", transactionService.getAccountByCustomerId(customerId));
		} catch (DataBaseException e) {
			return new ModelAndView("RetrieveCustomerAccount", "message", "SOME PROBLEM OCCUR TRY AGAIN LATER");
		}
	}
	
   /**
	 * <p>
	 *     This Method call to getCustomerAccount method in transactionService.
     *     Return to the RetrieveCustomerAccount JSP page with customer account or status message(failure).
     * </p>
     * 
	 * @param accountNumber
	 *     accountNumber of Account entered by user to view the corresponding record.
	 *     
	 * @return RetrieveCustomerAccount
	 *     Return to the RetrieveCustomerAccount JSP page with customer account detail or status message(failure).
	 */
	@RequestMapping(value = "/viewCustomerAccount", method = RequestMethod.GET)
	public ModelAndView viewCustomerAccount(@RequestParam("accountNumber") String accountNumber, ModelMap message) {
		try {
			return new ModelAndView("RetrieveCustomerAccount", "accountDetail",
					transactionService.getCustomerAccount(accountNumber));
		} catch (DataBaseException e) {
			return new ModelAndView("RetrieveCustomerAccount", "message", e.getMessage());
		}
	}
        
   /**
	 * <p>
	 *     Get the Customer Id from JSP page.
	 *     This Method call to getMiniStatementByCustomerId method in CustomerService.
     *     Return to the getMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
     * </p>
     * 
	 * @param customerId
	 *     Id of Customer entered by user to view the corresponding record(MiniStatement).
	 *     
	 * @return RetrieveMiniStatementByCustomerId
	 *     Return to the RetrieveMiniStatementByCustomerId JSP page with Customer MiniStatement or status message(failure).
	 * 
	 */
	@RequestMapping(value="/viewMiniStatementByCustomerId", method = RequestMethod.GET)  
    public ModelAndView viewMiniStatementByCustomerId (@RequestParam("customerId")String customerId, ModelMap message) {
        try {
        	return new ModelAndView("RetrieveMiniStatementByCustomerId", "miniStatement", transactionService.getCustomerMiniStatement(customerId));
        } catch (DataBaseException e) {
        	return new ModelAndView("CustomerIndex", "message", e.getMessage());
        }
	}
    
    /**
	 * <p>
	 *     It method used for approver permission active.
	 *     This Method call to transactionSuccess method in transactionService with transaction details.
	 *     It return to the  viewAllTransaction JSP page with status message (Success or failure).
	 * </p>
	 *       
	 * @param creditAccountNumber
	 *     creditAccountNumber of Account.
	 *     
	 * @param amount
	 *     amount of Transaction.
	 *           
	 * @param message
	 *     Display message using add attribute.
	 *     
	 * @return viewAllTransaction
	 *     Return to the viewAllTransaction JSP page with status message (Success or failure).
	 */ 
	@RequestMapping(value = "/transactionSuccess", method = RequestMethod.GET)
	public String transactionSuccess(@RequestParam("id") int transactionId,
			@RequestParam("creditAccountNumber") String creditAccountNumber, @RequestParam("amount") Double amount,
			@RequestParam("userId") String userId, ModelMap message) {
		try {
			transactionService.transactionSuccess(transactionId, creditAccountNumber, amount, userId);
			message.addAttribute("message", "TRANSACTION ACTION SUCCESSFULLY");
			message.addAttribute("notifications", transactionService.getAllNotification());
		} catch (DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
			return "RetrieveAllNotification";
		}
	}

    /**
	 * <p> 
	 *     It method used for approver ignore active.
	 *     This Method call to transactionFailure method in transactionService with transaction details.
	 *     It return to the  viewAllTransaction JSP page with status (Success or Failure).
	 * </p>
	 * 
	 * @param debitAccountNumber
	 *     debitAccountNumber of Account.
	 *     
	 * @param amount
	 *     amount of Transaction.
	 *           
	 * @param message
	 *     Display message using add attribute.
	 *  
	 * @return viewAllTransaction
	 *     Return to the viewAllTransaction JSP page with status (Success or Failure).
	 */ 
	@RequestMapping(value = "/transactionCancel", method = RequestMethod.GET)
	public String transactionFailure(@RequestParam("id") int transactionId,
			@RequestParam("debitAccountNumber") String debitAccountNumber, @RequestParam("amount") Double amount,
			@RequestParam("userId") String userId, ModelMap message) {
		try {
			transactionService.transactionFailure(transactionId, debitAccountNumber, amount, userId);
			message.addAttribute("transactions", "TRANSACTION ACTION SUCCESSFULLY");
			message.addAttribute("notifications", transactionService.getAllNotification());
		} catch (DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
			return "RetrieveAllNotification";
		}
	}
        
    /**
	 * <p> 
	 *     This form used to view transaction by date.
	 *     It is return to the viewTransactionBYDate JSP page.
	 * </p>
	 * 
	 * @return AddTransaction
	 *     Return to the AddTransaction JSP page.
	 *     
	 */
	@RequestMapping(value = "/viewTransactionByDate")
	public String getDateTransaction() {
		return "ViewTransactionByDate";
	}
        
    /**
	 * <p> 
	 *     It method used for view transaction by Date.
	 *     This Method call to getDateTransaction method in transactionService with transaction details.
	 *     Return to the viewTransactionBYDate JSP page with transaction lists or failure message.
	 * </p>
	 * 
	 * @param fromDate
	 *     fromDate of transaction
	 *     
	 * @param toDate
	 *     toDate of transaction.
	 *     
	 * @param message
	 *     Display message using add attribute.
	 *      
	 * @return viewTransactionByDate
	 *     Return to the viewTransactionBYDate JSP page with transaction lists or failure message.
	 */
	@RequestMapping(value = "/getDates", method = RequestMethod.GET)
	public String getDateTransaction(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate,
			ModelMap message) {
		try {
			message.addAttribute("transactions", transactionService.getDateTransaction(fromDate, toDate));
		} catch (DataBaseException e) {
			message.addAttribute("message", e.getMessage());
		} finally {
			return "ViewTransactionByDate";
		}
	}
}
