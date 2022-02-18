/**
 * 
 */
package com.ing.usermanagement.repository;
 
/**
 * @author prabuddha
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.usermanagement.model.User;
@Transactional(readOnly = true) 
public interface UserRepository extends JpaRepository<User, Long>{

	 
}
