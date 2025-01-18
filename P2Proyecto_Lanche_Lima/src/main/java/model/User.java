package model;

public class User {

    private String tfFullName;
    private String tfUsername;
    private String tfEmail;
    private String pfPassword;
    private String pfConfirmPassword;
    private String tfAddress;
    private String tfPhone;
    private String cbProfile; // cliente o administrador

    // constructor
    public User(String tfFullName, String tfUsername, String tfEmail, String pfPassword, String pfConfirmPassword, String tfAddress, String tfPhone, String cbProfile) {
        this.tfFullName = tfFullName;
        this.tfUsername = tfUsername;
        this.tfEmail = tfEmail;
        this.pfPassword = pfPassword;
        this.pfConfirmPassword = pfConfirmPassword;
        this.tfAddress = tfAddress;
        this.tfPhone = tfPhone;
        this.cbProfile = cbProfile;
    }
    
    //getter y setter
    public String getTfFullName() {
        return tfFullName;
    }

    public void setTfFullName(String tfFullName) {
        this.tfFullName = tfFullName;
    }

    public String getTfUsername() {
        return tfUsername;
    }

    public void setTfUsername(String tfUsername) {
        this.tfUsername = tfUsername;
    }

    public String getTfEmail() {
        return tfEmail;
    }

    public void setTfEmail(String tfEmail) {
        this.tfEmail = tfEmail;
    }

    public String getPfPassword() {
        return pfPassword;
    }

    public void setPfPassword(String pfPassword) {
        this.pfPassword = pfPassword;
    }

    public String getPfConfirmPassword() {
        return pfConfirmPassword;
    }

    public void setPfConfirmPassword(String pfConfirmPassword) {
        this.pfConfirmPassword = pfConfirmPassword;
    }

    public String getTfAddress() {
        return tfAddress;
    }

    public void setTfAddress(String tfAddress) {
        this.tfAddress = tfAddress;
    }

    public String getTfPhone() {
        return tfPhone;
    }

    public void setTfPhone(String tfPhone) {
        this.tfPhone = tfPhone;
    }

    public String getCbProfile() {
        return cbProfile;
    }

    public void setCbProfile(String cbProfile) {
        this.cbProfile = cbProfile;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "tfFullName='" + tfFullName + '\'' +
                ", tfUsername='" + tfUsername + '\'' +
                ", tfEmail='" + tfEmail + '\'' +
                ", pfPassword='" + pfPassword + '\'' +
                ", pfConfirmPassword='" + pfConfirmPassword + '\'' +
                ", tfAddress='" + tfAddress + '\'' +
                ", tfPhone='" + tfPhone + '\'' +
                ", cbProfile='" + cbProfile + '\'' +
                '}';
    }
}
