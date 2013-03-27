package com.shawn.enumNAnnotation;

import java.util.Date;
import java.util.List;

/**
 * User: Shawn cao
 * Date: 13-3-24
 * Time: PM6:52
 */
public class User {

    @CheckValue(String.class)
    private String userName;

    @CheckValue(String.class)
    private String password;

    @CheckValue(String.class)
    private String email;

    private List<Work> works;

    private List<Edu> edus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public List<Edu> getEdus() {
        return edus;
    }

    public void setEdus(List<Edu> edus) {
        this.edus = edus;
    }

    @Override
    public String toString() {
        return "UserFromResume [userName=" + userName + ", password="
                + password + ", email=" + email + ", works=" + works
                + ", edus=" + edus + "]";
    }

    public static class Work{
        @CheckValue(String.class)
        private String name;
        @CheckValue(String.class)
        private String position;
        @CheckValue(Long.class)
        private Long start;
        @CheckValue(Long.class)
        private Long end;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPosition() {
            return position;
        }
        public void setPosition(String position) {
            this.position = position;
        }
        public Long getStart() {
            return start;
        }
        public void setStart(Long start) {
            this.start = start;
        }
        public Long getEnd() {
            return end;
        }
        public void setEnd(Long end) {
            this.end = end;
        }
        @Override
        public String toString() {
            return "Work [name=" + name + ", position=" + position + ", start="
                    + start + ", end=" + end + "]";
        }

    }

    public static class Edu{
        @CheckValue(String.class)
        private String name;
        @CheckValue(String.class)
        private String degree;
        @CheckValue(Long.class)
        private Long start;
        @CheckValue(Long.class)
        private Long end;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDegree() {
            return degree;
        }
        public void setDegree(String degree) {
            this.degree = degree;
        }
        public Long getStart() {
            return start;
        }
        public void setStart(Long start) {
            this.start = start;
        }
        public Long getEnd() {
            return end;
        }
        public void setEnd(Long end) {
            this.end = end;
        }
        @Override
        public String toString() {
            return "Edu [name=" + name + ", degree=" + degree + ", start="
                    + start + ", end=" + end + "]";
        }
    }
}