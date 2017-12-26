package dao;

import entity.Barrage;
import entity.BarrageExample;
import entity.Video;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BarrageMapper {
    int countByExample(BarrageExample example);

    int deleteByExample(BarrageExample example);

    int deleteByPrimaryKey(Integer barrageid);

    int insert(Barrage record);

    int insertSelective(Barrage record);

    List<Barrage> selectByExample(BarrageExample example);//显示全部
    
    List<Barrage> selectById(@Param("userid") String userid);

    Barrage selectByPrimaryKey(Integer barrageid);

    int updateByExampleSelective(@Param("record") Barrage record, @Param("example") BarrageExample example);

    int updateByExample(@Param("record") Barrage record, @Param("example") BarrageExample example);

    int updateByPrimaryKeySelective(Barrage record);

    int updateByPrimaryKey(Barrage record);
    
    int insertBarrage(Barrage record);//免seq_id的insert Barrage
    
    List<Barrage> selectUnite(String userid);
}