package com.zemaitiz.gihub.mathgame.gg.repository;

import com.zemaitiz.gihub.mathgame.gg.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
