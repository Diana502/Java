package diana.maven.project.models;

public class PlatformRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_role.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_role.role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private String roleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_role.display_role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private String displayRoleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column platform_role.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    private Boolean isDel;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_role.id
     *
     * @return the value of platform_role.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_role.id
     *
     * @param id the value for platform_role.id
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_role.role_name
     *
     * @return the value of platform_role.role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_role.role_name
     *
     * @param roleName the value for platform_role.role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_role.display_role_name
     *
     * @return the value of platform_role.display_role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public String getDisplayRoleName() {
        return displayRoleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_role.display_role_name
     *
     * @param displayRoleName the value for platform_role.display_role_name
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setDisplayRoleName(String displayRoleName) {
        this.displayRoleName = displayRoleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column platform_role.is_del
     *
     * @return the value of platform_role.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column platform_role.is_del
     *
     * @param isDel the value for platform_role.is_del
     *
     * @mbg.generated Tue Apr 28 11:26:14 CST 2020
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}