package com.maingame.Controller;

import com.maingame.Model.Career.PlayerCareer;
import com.maingame.Model.Career.Student;
import com.maingame.Model.Career.Job;
import com.maingame.Model.Player;
import com.maingame.Service.CareerService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class CareerController {

    private CareerService careerService;
    private PlayerCareer playerCareer;
    private Player player;

    @FXML
    private StackPane careerContainer;

    @FXML
    private Text careerDetails;
    @FXML
    private Text institutionName;
    @FXML
    private VBox careerPage;

    @FXML
    private VBox collegeOptionsPage;
    @FXML
    private Button showCollegeOptions;
    @FXML
    private ListView<String> degreeListView = new ListView<>();

    @FXML
    private VBox workOptionsPage;
    @FXML
    private Button showWorkOptions;
    @FXML
    private ListView<String> workListView = new ListView<>();

    public void setPlayerCareer(CareerService careerService, PlayerCareer playerCareer, Player player) {
        this.careerService = careerService;
        this.playerCareer = playerCareer;
        this.player = player;
    }

    @FXML
    private void handleCloseAction() {
        MediaController.playButtonClick();

        if (careerContainer.getParent() instanceof VBox parentContainer) {
            parentContainer.setVisible(false);
        }
    }

    public void display() {
        if (player.getAge() >= 18) {
            showCollegeOptions.setVisible(true);
            showWorkOptions.setVisible(true);
        }

        if (playerCareer != null && playerCareer.getCurrentCareer() != null) {
            careerDetails.setText(playerCareer.getCurrentCareer().getDetails());
            institutionName.setText(playerCareer.getCurrentCareer().getInstitutionName());
        }
    }

    @FXML
    private void showCollegeOptions() {
        MediaController.playButtonClick();

        updateListView(degreeListView, careerService.getAvailableDegrees(), collegeOptionsPage);
        careerPage.setVisible(false);
        collegeOptionsPage.setVisible(true);
    }

    @FXML
    private void showWorkOptions() {
        MediaController.playButtonClick();

        updateListView(workListView, careerService.getAvailableJobs(), workOptionsPage);
        careerPage.setVisible(false);
        workOptionsPage.setVisible(true);
    }

    @FXML
    private void goBackToCareerPage() {
        MediaController.playButtonClick();

        careerPage.setVisible(true);
        collegeOptionsPage.setVisible(false);
        workOptionsPage.setVisible(false);
    }

    private void updateListView(ListView<String> listView, List<String> items, VBox optionsPage) {
        listView.setVisible(true);
        listView.getItems().clear();

        if (items != null && !items.isEmpty()) {
            listView.getItems().addAll(items);
        } else {
            listView.getItems().add("No available options at the moment.");
        }

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (listView == degreeListView) {
                    selectDegree();
                } else if (listView == workListView) {
                    selectWork();
                }
            }
        });

        if (!optionsPage.getChildren().contains(listView)) {
            optionsPage.getChildren().addFirst(listView);
        }
    }

    private void selectDegree() {
        String selectedDegree = degreeListView.getSelectionModel().getSelectedItem();
        String schoolName = careerService.getSchoolName(Student.StudentType.COLLEGE);

        if (selectedDegree != null && !selectedDegree.equals("No available degrees at the moment.")) {
            Student student = new Student(selectedDegree, Student.StudentType.COLLEGE, schoolName);
            playerCareer.updateCurrentCareer(student);

            careerPage.setVisible(true);
            collegeOptionsPage.setVisible(false);
            workOptionsPage.setVisible(false);
        }
    }

    private void selectWork() {
        String selectedWork = workListView.getSelectionModel().getSelectedItem();

        String jobName = getCareerName(selectedWork);
        Integer salary = this.careerService.getSalary(jobName);
        String company = this.careerService.getCompany(jobName);

        if (selectedWork != null && !selectedWork.equals("No available jobs at the moment.")) {
            Job job = new Job(jobName, salary, company);
            playerCareer.updateCurrentCareer(job);

            careerPage.setVisible(true);
            collegeOptionsPage.setVisible(false);
            workOptionsPage.setVisible(false);
        }
    }

   /** Find an alternative way to splice string for job name. */
    private String getCareerName(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        int separatorIndex = input.indexOf(" - ");
        return separatorIndex != -1 ? input.substring(0, separatorIndex) : input;
    }
}