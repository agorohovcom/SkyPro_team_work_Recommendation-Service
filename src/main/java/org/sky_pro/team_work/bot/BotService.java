package org.sky_pro.team_work.bot;

import org.sky_pro.team_work.bot.dto.UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Service
public class BotService {
    private final JdbcTemplate jdbcTemplate;

    public BotService(@Qualifier("recommendationsJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDto> findByUsername(String username) {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), username);
    }

    public static class UserRowMapper implements RowMapper<UserDto> {
        @Override
        public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDto userDto = new UserDto();
            userDto.setId(rs.getObject("ID", UUID.class));
            userDto.setUsername(rs.getString("USERNAME"));
            userDto.setFirstName(rs.getString("FIRST_NAME"));
            userDto.setLastName(rs.getString("LAST_NAME"));
            return userDto;
        }
    }
}
