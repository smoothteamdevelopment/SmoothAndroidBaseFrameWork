package com.baidu.push.example.location;
import java.util.Date;
/**
 * 项目名称：
 * 功能描述：
 * 创建人:ahtt_nsj
 * 创建时间:2014/7/15 0015 10:44
 * 修改人：
 * 修改时间:
 *
 * @版本：V
 */

public class LocationUpdate {
    Contact contact;
    Position position;
    Date timestamp;
    Date received;
    public LocationUpdate(Contact contcat, Position position) {
        this.contact = contact;
        this.position = position;
    }
    public LocationUpdate(Position position) {
        this.position = position;
        received = new Date();
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationUpdate that = (LocationUpdate) o;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return true;
    }
    @Override
    public int hashCode() {

        int result = contact != null ? contact.hashCode() : 0;

        result = 31 * result + (position != null ? position.hashCode() : 0);

        return result;

    }

    @Override

    public String toString() {

        return "LocationUpdate{" +

        "contact=" + contact +

        ", position=" + position +

        '}';

    }

    public Contact getContact() {

        return contact;

    }

    public void setContact(Contact contact) {

        this.contact = contact;

    }

    public Position getPosition() {

        return position;

    }
    public void setPosition(Position position) {

        this.position = position;

    }
}
