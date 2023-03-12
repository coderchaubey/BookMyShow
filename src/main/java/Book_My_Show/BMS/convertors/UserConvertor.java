package Book_My_Show.BMS.convertors;

import Book_My_Show.BMS.EntryDtos.UserEntryDto;
import Book_My_Show.BMS.Model.User;

public class UserConvertor {
     //Static is kept to avoid calling it via objects/instances
     public static User convertDtoToEntity(UserEntryDto userEntryDto){
               User user= User.builder().age(userEntryDto.getAge()).name(userEntryDto.getName()).
                address(userEntryDto.getAddress()).
                email(userEntryDto.getEmail())
                .mobNo(userEntryDto.getMobNo()).build();

               return user;
     }
}
