/**
 * 
 */
package com.ing.usermanagement.repository;
 
/**
 * @author prabuddha
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;


import com.ing.usermanagement.model.User;
public interface UserRepository extends JpaRepository<User, Long>{

	 
}
