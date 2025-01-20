package org.example.repo;

import org.example.model.Good;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Repo {
    private final JdbcTemplate template;

    public Repo(JdbcTemplate template) {
        this.template = template;
    }

    public Good addGood (Good good){
        try {
            template.update("INSERT INTO cart (id, name) VALUES (? , ?)", good.getId(), good.getName());
            return good;
        }catch (DataAccessException e){
            return null;
        }
    }

    public Good removeGood(Good good){
        try {
            template.update("DELETE FROM cart WHERE id = ?", good.getId());
            return good;
        }catch (DataAccessException e){
            return null;
        }
    }

    public List<Good> findAll(){
        RowMapper<Good> mapper = (r, i) -> {
            Good good = new Good();
            good.setId(r.getInt("id"));
            good.setName(r.getString("name"));

            return good;
        };
        return template.query("SELECT * FROM cart", mapper);
    }
}
