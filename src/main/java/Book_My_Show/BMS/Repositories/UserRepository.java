package Book_My_Show.BMS.Repositories;

import Book_My_Show.BMS.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
