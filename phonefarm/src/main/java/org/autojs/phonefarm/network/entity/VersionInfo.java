package org.autojs.phonefarm.network.entity;


import org.autojs.phonefarm.BuildConfig;

/**
 * Created by Stardust on 2017/9/20.
 */

public class VersionInfo {

    public int versionCode;
    public String version;
    public String apkurl;
    public String description;
    public String note;

    public boolean isNewer() {
        return versionCode > BuildConfig.VERSION_CODE;
    }

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "versionCode=" + versionCode +
                ", releaseNotes='" + description + '\'' +
                ", versionName='" + version + '\'' +
                ", downloadUrl='" + apkurl + '\'' +
                '}';
    }
}
