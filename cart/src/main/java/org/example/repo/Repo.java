package org.example.repo;

import org.example.model.Good;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
            good.setPrice(r.getDouble("price"));

            return good;
        };
        return template.query("SELECT * FROM cart", mapper);
    }

    public Boolean flush(){
        try {
            template.update("DELETE FROM cart");
            return true;
        }catch (DataAccessException e){
            return false;
        }
    }

    public Good getGoodById(int id){
        ResultSetExtractor<Good> extractor = (r) -> {
          Good good = new Good();
          good.setId(r.getInt("id"));
          good.setName(r.getString("name"));
          good.setPrice(r.getDouble("price"));

          return good;
        };

        return template.query("SELECT * FROM cart WHERE id = ?", new Object[]{id}, extractor);
    }
}
