package sg.edu.nus.iss.vttp5a_ssf_day13L.repo;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5a_ssf_day13L.model.Person;

@Repository
public class PersonRepo {

    private List<Person> persons = new ArrayList<>();

    
    // in constructor??

    public PersonRepo() throws ParseException {

        String birthDate = "1988-12-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse(birthDate);
        persons.add(new Person("Daniel", "Loo", "danielloo@ial.edu.sg", 12000, birthday, "93806836", 123456));
    }

    


    public List<Person> findAll() {
        return persons;
    }

    public Person findById(String personId) {
        Person foundPerson = persons.stream().filter(p -> p.getId().equals(personId)).findFirst().get();

        return foundPerson;
    }

   

    public Boolean create(Person person) {
        persons.add(person);
        return true;
    }

    public Boolean delete(Person person) {
        int iFoundPerson = persons.indexOf(person);

        if (iFoundPerson >= 0) {
            persons.remove(person);
            return true;
        }

        return false;
    }

    public Boolean update(Person person) {
        List<Person> filteredPerson = persons.stream()
                                            .filter(p -> p.getId().equals(person.getId()))
                                            .collect(Collectors.toList());

        if (filteredPerson.size() > 0) {
            persons.remove(filteredPerson.getFirst());
            persons.add(person);
            return true;
        }

        return false;
    }

    public Path createFilePath() throws IOException {

        String directory = "/Users/maziyyah/documents/visa_vttp/02_SSF/workshops";
        String fileName = "/day13Lecture";

        Path filePath = Paths.get(directory, fileName);

        if(!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        return filePath;

    }

    public void writeToCSV() {
        // need a directory

        // need to get the List<Person>
        // List<Person> persons is a PersonRepo field
        // all my info is in here

        // create new file??

        String directory = "/Users/maziyyah/documents/visa_vttp/02_SSF/workshops";
        String fileName = "/day13Lecture";

        String dirFileName = directory + File.separator + fileName;

        Path file = Paths.get(dirFileName);

        try {
            
            Files.createFile(file);

        } catch (FileAlreadyExistsException x) {
            System.err.format("file name %s" + " already exists%n", file);
        } catch (IOException x) {
            System.err.format("createFile error: %s%n", x);
        }


        

        //create a file

        // need a bufferedwriter
    }


    public void readCSV() {

    }

    

    
}
