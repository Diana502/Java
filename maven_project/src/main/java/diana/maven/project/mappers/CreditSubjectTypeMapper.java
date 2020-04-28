package diana.maven.project.mappers;

import diana.maven.project.models.CreditSubjectType;
import diana.maven.project.models.CreditSubjectTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditSubjectTypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    long countByExample(CreditSubjectTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int deleteByExample(CreditSubjectTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int insert(CreditSubjectType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int insertSelective(CreditSubjectType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    List<CreditSubjectType> selectByExample(CreditSubjectTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    CreditSubjectType selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByExampleSelective(@Param("record") CreditSubjectType record, @Param("example") CreditSubjectTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByExample(@Param("record") CreditSubjectType record, @Param("example") CreditSubjectTypeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByPrimaryKeySelective(CreditSubjectType record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table credit_subject_type
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    int updateByPrimaryKey(CreditSubjectType record);
}