/**
 * 
 */
package com.ing.usermanagement.repository;
 
import java.util.Optional;

/**
 * @author prabuddha
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ing.usermanagement.model.User;
@Transactional(readOnly = true) 
public interface UserRepository extends JpaRepository<User, Long>{

	 Optional<User> findByEmployeeId(Long id);
}
