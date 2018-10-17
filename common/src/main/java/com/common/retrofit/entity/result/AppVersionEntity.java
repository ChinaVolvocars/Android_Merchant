package com.common.retrofit.entity.result;

/**
 * Created by little_bug on 2018/10/14.
 */
public class AppVersionEntity {

    /**
     * "versioncode"（版本号）: "1.0.5",
      "downloadUrl"（安卓安装包地址）: "123123",
      "description"（版本描述）: "123123",
      "force"（是否强制 0不需要更新，1不强制，2强制）: "1"
     */
    private String versioncode;
    private String downloadUrl;
    private String description;
    private String force;

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * （是否强制 0不需要更新，1不强制，2强制）: "1"
     *  0不需要更新  <br/>
     *  1不强制  <br/>
     *  2强制  <br/>
     * @return
     */
    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    @Override
    public String toString() {
        return "AppVersionEntity{" +
                "versioncode='" + versioncode + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", description='" + description + '\'' +
                ", force='" + force + '\'' +
                '}';
    }
}
