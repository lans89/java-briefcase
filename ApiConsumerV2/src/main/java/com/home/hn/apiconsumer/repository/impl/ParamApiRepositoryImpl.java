package com.home.hn.apiconsumer.repository.impl;

import com.home.hn.apiconsumer.exception.ConfigurationApiNotFoundException;
import com.home.hn.apiconsumer.model.repository.ApiDetailModel;
import com.home.hn.apiconsumer.model.repository.ApiModel;
import com.home.hn.apiconsumer.repository.ParamApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ParamApiRepositoryImpl implements ParamApiRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    private final String SQL_API = "select id, apihost, apiport, apipath, apimethod, apiname from api_header where apiname = :name";
    private final String SQL_HEADER = "select header_name name_param, default_value , required from api_detail_header where id_api_header =:id and required = 0";
    private final String SQL_QUERY_PARAMS = "select query_name name_param, default_value , required from api_detail_queryparam where id_api_header =:id and required = 0";

    @Override
    public Optional<ApiModel> getApiModel(String name) {
        Optional<ApiModel> model;
        ApiModel apiModel = null;

        try{
            apiModel = jdbcTemplate.queryForObject(
                    SQL_API,
                    new MapSqlParameterSource("name", name),
                    new ApiModelRowMapper());
        }catch (EmptyResultDataAccessException e){
            apiModel =null;
        }finally{
            model = Optional.ofNullable(apiModel);
        }

        if(!model.isEmpty()){
            Optional.of(
                    jdbcTemplate.query(
                            SQL_HEADER,
                            new MapSqlParameterSource("id", model.get().getId()),
                            new ApiDetailModelRowMapper()))
                    .ifPresent(h -> model.get().setHeaders(h));
            Optional.of(
                            jdbcTemplate.query(
                                    SQL_QUERY_PARAMS,
                                    new MapSqlParameterSource("id", model.get().getId()),
                                    new ApiDetailModelRowMapper()))
                    .ifPresent(q -> model.get().setQueryParams(q));
        }

        return model;
    }

    class ApiModelRowMapper implements RowMapper<ApiModel>{

        @Override
        public ApiModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ApiModel.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("apiname"))
                    .path(rs.getString("apipath"))
                    .host(rs.getString("apihost"))
                    .port(rs.getInt("apiport"))
                    .method(rs.getString("apimethod"))
                    .build();
        }
    }

    class ApiDetailModelRowMapper implements RowMapper<ApiDetailModel>{
        @Override
        public ApiDetailModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return ApiDetailModel.builder()
                    .name(rs.getString("name_param"))
                    .defaultValue(rs.getString("default_value"))
                    .required(rs.getInt("required")==0?true:false)
                    .build();
        }
    }
}
