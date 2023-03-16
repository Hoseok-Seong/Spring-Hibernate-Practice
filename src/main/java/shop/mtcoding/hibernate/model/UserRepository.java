package shop.mtcoding.hibernate.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional // 원래는 서비스에 붙이기
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Transactional
    public User update(User user) {
        return em.merge(user);
    }

    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll(int page, int row) { // jpql이라는 문법 사용.
        return em.createQuery("select u from User u", User.class).setFirstResult(page * 2).setMaxResults(2)
                .getResultList();
    }

}
