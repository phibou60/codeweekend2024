package commons;

import java.util.List;

public class Response {

    List<String> submission;
    int score;
    
    public Response(List<String> submission, int score) {
        this.submission = submission;
        this.score = score;
    }

    public List<String> getSubmission() {
        return submission;
    }

    public void setSubmission(List<String> submission) {
        this.submission = submission;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
