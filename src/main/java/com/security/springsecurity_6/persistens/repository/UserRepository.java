package com.security.springsecurity_6.persistens.repository;

import com.security.springsecurity_6.persistens.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
