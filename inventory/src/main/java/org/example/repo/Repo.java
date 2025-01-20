package org.example.repo;

import org.example.model.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Repo {
    private final JdbcTemplate template;

    public Repo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Good> findAll(){
        RowMapper<Good> mapper = (r, i) -> {
            Good good = new Good();
            good.setId(r.getInt("id"));
            good.setName(r.getString("name"));

            return good;
        };
        return template.query("SELECT * FROM goods", mapper);
    }

    public Boolean grab (Good good){
        RowMapper<Good> mapper = (r, i) -> {
            Good grabed = new Good();
            grabed.setId(r.getInt("id"));
            grabed.setName(r.getString("name"));

            return grabed;
        };
        try {
            template.update("DELETE FROM goods WHERE id = ?", good.getId());
            return true;
        } catch (DataAccessException e){
            return false;
        }
    }

    public Boolean returnGood(Good good){
        try {
            template.update("INSERT INTO goods (id, name) VALUES (? , ?)", good.getId(), good.getName());
            return true;
        } catch (DataAccessException e){
            return false;
        }
    }
}
