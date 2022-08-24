package org.example.project1.DAO;

import org.example.project1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_fio=?", new Object[] {fio},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO Person(person_fio, person_year_born) VALUES  (?, ?)", person.getPersonFio(), person.getPersonYearBorn());
    }

    public int update(int id, Person updatedPerson) {
        return jdbcTemplate.update("UPDATE Person SET person_fio=?, person_year_born=? WHERE person_id=?", updatedPerson.getPersonFio(),
                updatedPerson.getPersonYearBorn(), id);

    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }
}
