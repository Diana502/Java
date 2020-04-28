package diana.maven.project.mappers;

import diana.maven.project.models.CreditRecord;
import diana.maven.project.models.CreditRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    long countByExample(CreditRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int deleteByExample(CreditRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int insert(CreditRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int insertSelective(CreditRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    List<CreditRecord> selectByExample(CreditRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    CreditRecord selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") CreditRecord record, @Param("example") CreditRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByExample(@Param("record") CreditRecord record, @Param("example") CreditRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByPrimaryKeySelective(CreditRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_record
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByPrimaryKey(CreditRecord record);
}