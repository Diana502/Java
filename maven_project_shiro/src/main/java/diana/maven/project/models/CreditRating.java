package diana.maven.project.models;

public class CreditRating {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_rating.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_rating.rating_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private String ratingName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_rating.min_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Integer minPoints;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_rating.max_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Integer maxPoints;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column credit_rating.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Boolean isDel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_rating.id
     *
     * @return the value of credit_rating.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_rating.id
     *
     * @param id the value for credit_rating.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_rating.rating_name
     *
     * @return the value of credit_rating.rating_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public String getRatingName() {
        return ratingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_rating.rating_name
     *
     * @param ratingName the value for credit_rating.rating_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_rating.min_points
     *
     * @return the value of credit_rating.min_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Integer getMinPoints() {
        return minPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_rating.min_points
     *
     * @param minPoints the value for credit_rating.min_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setMinPoints(Integer minPoints) {
        this.minPoints = minPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_rating.max_points
     *
     * @return the value of credit_rating.max_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Integer getMaxPoints() {
        return maxPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_rating.max_points
     *
     * @param maxPoints the value for credit_rating.max_points
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setMaxPoints(Integer maxPoints) {
        this.maxPoints = maxPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column credit_rating.is_del
     *
     * @return the value of credit_rating.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column credit_rating.is_del
     *
     * @param isDel the value for credit_rating.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}