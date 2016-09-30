package com.i2i.netbankingapplication.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.i2i.netbankingapplication.dao.CustomerTransactionDao;
import com.i2i.netbankingapplication.exception.DataBaseException;
import com.i2i.netbankingapplication.model.Account;
import com.i2i.netbankingapplication.model.CustomerTransaction;


/**
 * <p>
 *     When request comes from CustomerTransactionManager. TransactionDao performs add or delete or fetch or fetchAll 
 *     with database and return the responses to TransactionService.
 *     It handles the HibernateException and DataBaseException.
 *     Connect to the HibernateConnection class use to create hibernate connection.
 * </p>
 * 
 * @author TEAM-2
 * 
 * @created 2016-09-27
 */
@Repository("customerTransactionDao")
@Transactional
public class CustomerTransactionHibernate extends GenericDaoHibernate<CustomerTransaction, Long> implements CustomerTransactionDao {
    
    /**
     * Constructor to create a Generics-based version using Branch as the entity
     */
    public CustomerTransactionHibernate() {
        super(CustomerTransaction.class);
    }

    public void insertTransaction(CustomerTransaction customerTransaction, Account debitAccount) throws DataBaseException{
        try {
            Session session = getSession();
            session.save(customerTransaction);
            session.update(debitAccount);
        } catch (HibernateException e) {
            throw new DataBaseException("PLEASE CHECK YOUR DATAS YOUR DATA IS NOT VALID.PLEASE TRY AGAIN.addTransaction" );  
        }
    }

    /**
     * <p>
     *     Retrieves all CustomerTransactions from database.
     *     Return all CustomerTransactions in List type.
     * </p>
     * 
     * @return list
     *     return the list of Customer Transactions.
     * 
     * @throws DataBaseException
     *     It handle all the custom exception in NetBanking Application and HibernateException.
     */
    public List<CustomerTransaction> retriveAllTransactions() throws DataBaseException {
        try {
            return getSession().createQuery("FROM CustomerTransaction").list();
        } catch (HibernateException e) {
            throw new DataBaseException("TRANSACTIONS IS NOT AVAILABLE..");
        }
    }

    @Override
    public CustomerTransaction retriveTransactionById(int id) throws DataBaseException {
        return (CustomerTransaction)getSession().get(CustomerTransaction.class, id); 
    }
    
    public void updateTransactionStatus(Account account,CustomerTransaction customerTransaction) 
            throws DataBaseException {
        try {
            Session session = getSession();
            session.update(account);
            session.update(customerTransaction);
        } catch (HibernateException e) {
            throw new DataBaseException("OOPS SOME PROBLEM OCCURED.. PLEASE TRY AGAIN LATER");
        }
    }
}
