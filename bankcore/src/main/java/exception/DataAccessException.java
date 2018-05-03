/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;

/**
 *
 * @author user
 */
public class DataAccessException extends RuntimeException {

    public DataAccessException() {
    }

    public DataAccessException(Throwable cause) {
        super (cause);
    }

}
