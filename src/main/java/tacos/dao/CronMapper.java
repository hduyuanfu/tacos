package tacos.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yuanfu
 * @data 2021/5/14 20:15
 */
@Repository
public interface CronMapper {

    @Select("select cron from scheduled where cron_id = #{id}")
    public String getCron(int id);
}
