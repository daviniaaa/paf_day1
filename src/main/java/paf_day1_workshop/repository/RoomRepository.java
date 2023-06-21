package paf_day1_workshop.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import paf_day1_workshop.model.Room;

@Repository
public class RoomRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private String countSql = "select count(*) from room";
    private String findAllSql = "select * from room";
    private String findByIdSql = "select * from room where id = ?";
    private String insertSql = "insert into room (room_type, price) values (?, ?)";
    private String deleteByIdSql = "delete from room where id = ?";
    private String updateSql = "update room set price =? where id = ?";

    public int count() {
        Integer result = 0;
        result = jdbcTemplate.queryForObject(countSql, Integer.class);
        return result;
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        roomList = jdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Room.class));

        return roomList;
    }

    public Room getRoomById(int id) {
        Room room = new Room();

        room = jdbcTemplate.queryForObject(findByIdSql, 
            BeanPropertyRowMapper.newInstance(Room.class), id);
        
        return room;
    }

    public Boolean save(Room room) {
        Boolean saved = false;

        saved = jdbcTemplate.execute(insertSql, new PreparedStatementCallback<Boolean>() {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1, room.getRoomType()); // starts from 1, (room_type, price)
                ps.setInt(2, room.getPrice());
                return ps.execute();
            }
            
        });

        return saved;
    }

    public int update(Room room) {
        int updated = 0;
        updated = jdbcTemplate.update(updateSql, room.getPrice(), room.getId());
        return updated;
    }

    public int deleteById(int id) {
        int deleted = 0;
        deleted = jdbcTemplate.update(deleteByIdSql, id);
        return deleted;
    }
}
