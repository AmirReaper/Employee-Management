package com.example.model;

/**
 * Interface for employees that can attend training sessions
 */
public interface Trainable {
    void attendTraining(String topic);

    /**
     * NEW in v2.1.0 - Default method for training completion
     */
    default String completeTraining(String topic) {
        return "Training completed: " + topic;
    }

    /**
     * NEW in v2.1.0 - Static method for available trainings
     */
    static String[] getAvailableTrainings() {
        return new String[] {
                "Java Programming",
                "Soft Skills",
                "Project Management",
                "Team Leadership",
                "Technical Writing"
        };
    }
}