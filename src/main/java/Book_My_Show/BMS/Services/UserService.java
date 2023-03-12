package Book_My_Show.BMS.Services;

import Book_My_Show.BMS.EntryDtos.UserEntryDto;
import Book_My_Show.BMS.Model.User;
import Book_My_Show.BMS.Repositories.UserRepository;
import Book_My_Show.BMS.convertors.UserConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String addUser(UserEntryDto userEntryDto)throws Exception{
        User user= UserConvertor.convertDtoToEntity(userEntryDto);
        userRepository.save(user);

        return "User Added Successfully";
    }

    //My Code

    public String getUserName(@RequestParam int id){

            return userRepository.findById(id).get().getName();

    }

}
//Here we need to convert and save.
//        /*
//        old method: create an object and set attributes.
//         */
//
//        User user =User.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).
//                address(userEntryDto.getAddress()).
//                email(userEntryDto.getEmail())
//                .mobNo(userEntryDto.getMobNo()).build();
//
//        userRepository.save(user);
//    }
