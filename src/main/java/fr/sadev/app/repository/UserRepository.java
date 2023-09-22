package fr.sadev.app.repository;

import javax.persistence.EntityManagerFactory;

import fr.sadev.app.beans.User;

public class UserRepository extends EntityRepository<User, Long> {

	public UserRepository(EntityManagerFactory emf, Class<User> clazz) {
		super(emf, clazz);
	}

}
