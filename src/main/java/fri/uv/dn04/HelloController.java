package fri.uv.dn04;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class HelloController implements Initializable {
    public DatePicker datumIzposoje;
    public DatePicker datumVrnitve;
    public ComboBox velicinaAvta;
    public ComboBox tipMenjalnika;
    public ComboBox casIzposoje1;
    public ComboBox casIzposoje2;
    public ComboBox casVrnitve1;
    public ComboBox casVrnitve2;
    public ComboBox avto;
    public ComboBox tipMotorja;
    public TextField cena;
    public TextField imePriimekKartica;
    @FXML
    public TextField stevilkaKartice;
    public TextField naslovNarocnik;
    public TextField imePriimekNarocnik;
    public CheckBox dodatnoVarovanje;
    public TextField emailNarocnik;
    public TextField telefonNarocnik;
    public Spinner starostNarocnik;
    public Spinner vozniskoNarocnik;
    public TextField cvv;
    public Label status;
    public DatePicker datumIzteka;
    public Button odpriLabel;
    public Button shraniLabel;
    public Button poanstaviLabel;
    public Button pomocLabel;
    public Button izhodLabel;
    public Label datumIzposojeLabel;
    public Label datumVrnitveLabel;
    public Label velicinaAvtaLabel;
    public Label tipMenjalnikaLabel;
    public Label casVrnitveLabel;
    public Label tipMotorjaLabel;
    public Label avtoLabel;
    public Label cenaLabel;
    public Label kreditnaKarticaLabel;
    public Label imePriimekKarticaLabel;
    public Label stevilkaKarticeLabel;
    public Label naslovNarocnikLabel;
    public Label imePriimekNarocnikLabel;
    public Label narocnikLabel;
    public Label emailLabel;
    public Label telefonLabel;
    public Label starostLabel;
    public Label vozniskoLabel;
    public Button potrdiNarociloLabel;
    public Label datumIztekaLabel;
    public boolean english = false;
    public Label casIzposojeLabel;
    public Button prevedi;
    public ProgressBar progress;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avto.setDisable(true);
        tipMenjalnika.setDisable(true);
        tipMotorja.setDisable(true);
        datumVrnitve.setDisable(true);
        casVrnitve1.setDisable(true);
        casVrnitve2.setDisable(true);
        createTextFormatter(stevilkaKartice, 16);
        createTextFormatter(cvv, 3);

        ObservableList<String> hours = FXCollections.observableArrayList();
        ObservableList<String> minutes = FXCollections.observableArrayList();
        starostNarocnik.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(18, 100));
        vozniskoNarocnik.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 80));
        velicinaAvta.setItems(FXCollections.observableArrayList("Majhen", "Srednji", "Velik"));
        tipMenjalnika.setItems(FXCollections.observableArrayList("Ročni", "Avtomatski"));
        tipMotorja.setItems(FXCollections.observableArrayList("Dizelski", "Bencinski", "Elektricni"));


        for (int i = 0; i < 24; i++)
            hours.add(String.format("%02d", i));

        for (int i = 0; i < 60; i++)
            minutes.add(String.format("%02d", i));

        casIzposoje1.setItems(hours);
        casIzposoje2.setItems(minutes);
        casVrnitve1.setItems(hours);
        casVrnitve2.setItems(minutes);

        velicinaAvta.valueProperty().addListener((obs, oldV, newV) -> {
            if (newV == "Majhen"){
                avto.setValue(null);
                tipMotorja.setValue(null);
                tipMenjalnika.setValue(null);
                tipMotorja.setDisable(false);
                tipMotorja.valueProperty().addListener((obsMotor, oldMotor, newMotor) -> {
                    tipMenjalnika.setDisable(false);
                    if (newMotor == "Dizelski"){
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                            avto.setDisable(false);
                            if (newMenjalnik == "Ročni")
                                avto.setItems(FXCollections.observableArrayList("Toyota Yaris", "Hyundai i10"));
                            else avto.setItems(FXCollections.observableArrayList("Renault Twingo"));
                        });
                    } else if (newMotor == "Bencinski"){
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                        if (newMenjalnik == "Ročni")
                            avto.setItems(FXCollections.observableArrayList("Ford Fiesta"));
                        else avto.setItems(FXCollections.observableArrayList("Volkswagen Polo", "Hyundai i20", "Renault Clio"));
                    });
                    } else {
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.setValue("Avtomatski");
                        tipMenjalnika.setDisable(true);
                        avto.setItems(FXCollections.observableArrayList("Nissan Leaf", "Renault Zoe", "BMW i3"));
                    }
                });
            } else if (newV == "Srednji"){
                avto.setValue(null);
                tipMotorja.setValue(null);
                tipMenjalnika.setValue(null);
                tipMotorja.setDisable(false);
                tipMotorja.valueProperty().addListener((obsMotor, oldMotor, newMotor) -> {
                    tipMenjalnika.setDisable(false);
                    if (newMotor == "Dizelski"){
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                            avto.setDisable(false);
                            if (newMenjalnik == "Ročni")
                                avto.setItems(FXCollections.observableArrayList("Golf 5"));
                            else avto.setItems(FXCollections.observableArrayList("Golf 7", "SEAT Leon"));
                        });
                    } else if (newMotor == "Bencinski"){
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                            if (newMenjalnik == "Ročni")
                                avto.setItems(FXCollections.observableArrayList("Ford Fusion", "Toyota Camry"));
                            else avto.setItems(FXCollections.observableArrayList("Golf 7", "Mazda6"));
                        });
                    } else {
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.setValue("Avtomatski");
                        tipMenjalnika.setDisable(true);
                        avto.setItems(FXCollections.observableArrayList("Tesla 3"));
                    }
                });
            } else {
                avto.setValue(null);
                tipMotorja.setValue(null);
                tipMenjalnika.setValue(null);
                tipMotorja.setDisable(false);
                tipMotorja.valueProperty().addListener((obsMotor, oldMotor, newMotor) -> {
                    tipMenjalnika.setDisable(false);
                    if (newMotor == "Dizelski"){
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                            avto.setDisable(false);
                            if (newMenjalnik == "Ročni")
                                avto.setItems(FXCollections.observableArrayList("Nissan Navara"));
                            else avto.setItems(FXCollections.observableArrayList("Toyota Land Cruiser", "BMW X5", "Mercedes GLE"));
                        });
                    } else if (newMotor == "Bencinski"){
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.valueProperty().addListener((obsMenjalnik, oldMenjalnik, newMenjalnik) -> {
                            if (newMenjalnik == "Ročni")
                                avto.setItems(FXCollections.observableArrayList("Ford Ranger"));
                            else avto.setItems(FXCollections.observableArrayList("Volvo XC90", "Audi Q7"));
                        });
                    } else {
                        avto.setDisable(false);
                        avto.setValue(null);
                        tipMenjalnika.setValue("Avtomatski");
                        tipMenjalnika.setDisable(true);
                        avto.setItems(FXCollections.observableArrayList("Tesla Model X", "Porsche Taycan"));
                    }
                });}
        });

        // Inside the listener for the avto ComboBox
        avto.valueProperty().addListener((obsAvto, oldAvto, newAvto) -> {
            if (newAvto != null) {
                int basePrice;
                int additionalInsurancePrice = 0;

                if (newAvto.equals("Renault Clio")) {
                    basePrice = 30;
                } else if (newAvto.equals("Toyota Yaris")) {
                    basePrice = 25;
                } else if (newAvto.equals("Hyundai i10")) {
                    basePrice = 28;
                } else if (newAvto.equals("Renault Twingo")) {
                    basePrice = 32;
                } else if (newAvto.equals("Golf 5")) {
                    basePrice = 40;
                } else if (newAvto.equals("Golf 7")) {
                    basePrice = 70;
                } else if (newAvto.equals("SEAT Leon")) {
                    basePrice = 55;
                } else if (newAvto.equals("BMW X5")) {
                    basePrice = 100;
                } else if (newAvto.equals("Ford Fusion")) {
                    basePrice = 72;
                } else if (newAvto.equals("Porsche Taycan")) {
                    basePrice = 195;
                } else if (newAvto.equals("Audi Q7")) {
                    basePrice = 170;
                } else if (newAvto.equals("Audi A5")) {
                    basePrice = 82;
                } else if (newAvto.equals("Mercedes GLE")) {
                    basePrice = 78;
                } else if (newAvto.equals("Ford Ranger")) {
                    basePrice = 150;
                } else if (newAvto.equals("Nissan Leaf")) {
                    basePrice = 42;
                } else if (newAvto.equals("Renault Zoe")) {
                    basePrice = 37;
                } else if (newAvto.equals("BMW i3")) {
                    basePrice = 45;
                } else if (newAvto.equals("Volvo XC90")) {
                    basePrice = 125;
                } else if (newAvto.equals("Ford Fiesta")) {
                    basePrice = 75;
                } else if (newAvto.equals("Volkswagen Polo")) {
                    basePrice = 52;
                } else if (newAvto.equals("Hyundai i20")) {
                    basePrice = 50;
                } else if (newAvto.equals("Ford Fusion")) {
                    basePrice = 82;
                } else if (newAvto.equals("Toyota Camry")) {
                    basePrice = 66;
                } else if (newAvto.equals("Mazda6")) {
                    basePrice = 86;
                } else if (newAvto.equals("Tesla 3")) {
                    basePrice = 100;
                } else if (newAvto.equals("Tesla Model X")){
                    basePrice = 184;
                } else if (newAvto.equals("Nissan Navara")) {
                    basePrice = 98;
                } else if (newAvto.equals("Toyota Land Cruiser")){
                    basePrice = 134;
                }
                else {
                    basePrice = 0;
                }

                dodatnoVarovanje.selectedProperty().addListener((obs, oldV, newV) -> {
                    int insurance;
                    if (newV != null){
                        int days = (int) ChronoUnit.DAYS.between(datumIzposoje.getValue(), datumVrnitve.getValue());
                        insurance = 2 * days;
                        int totalPrice = basePrice + insurance;
                        cena.setText(String.format("%d €", totalPrice));
                    } else {
                        insurance = 0;
                        int totalPrice = basePrice + insurance;
                        cena.setText(String.format("%d €", totalPrice));
                    }
                });

                int totalPrice = basePrice + additionalInsurancePrice;

                cena.setText(String.format("%d €", totalPrice));
            }
        });

        progress.setProgress(0.0);
        addListeners();

        datumIzteka.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #D3D3D3;");
                }
            }
        });

        datumIzposoje.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #D3D3D3;");
                }
            }
        });

        datumIzposoje.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                final LocalDate minDate = newDate.plusDays(1);
                datumVrnitve.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        if (date.isBefore(minDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #D3D3D3;");
                        }
                    }
                });
            }
        });

        datumVrnitve.setDisable(true);
        casVrnitve1.setDisable(true);
        casVrnitve2.setDisable(true);
        casIzposoje2.valueProperty().addListener((obs, oldV, newV) -> {
            datumVrnitve.setDisable(newV == null);
            casVrnitve1.setDisable(newV==null);
            casVrnitve2.setDisable(newV==null);
        });


    }

    public void createTextFormatter(TextField tf, int n){
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() <= n) return change;
            else return null;
        });
        tf.setTextFormatter(textFormatter);
    }

    public void odpriCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi datoteko za odpiranje");
        File f = fc.showOpenDialog(null);
        if (f != null) {
            status.setTextFill(BLACK);
            status.setText("Odprta datoteka: " + f.getName() + ", " + f.length() + " B");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.startsWith("*") && !line.trim().isEmpty()) {
                        String[] parts = line.split(": ");
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        switch (key) {
                            case "Datum izposoje" -> datumIzposoje.setValue(LocalDate.parse(value));
                            case "Datum vrnitve" -> datumVrnitve.setValue(LocalDate.parse(value));
                            case "Velikost avta" -> velicinaAvta.getSelectionModel().select(value);
                            case "Tip menjalnika" -> tipMenjalnika.getSelectionModel().select(value);
                            case "Čas izposoje" -> {
                                String value2 = parts[2].trim();
                                casIzposoje1.getSelectionModel().select(value);
                                casIzposoje2.getSelectionModel().select(value2);
                            }
                            case "Čas vrnitve" -> {
                                String value2 = parts[2].trim();
                                casVrnitve1.getSelectionModel().select(value);
                                casVrnitve2.getSelectionModel().select(value2);
                            }
                            case "Avto" -> avto.getSelectionModel().select(value);
                            case "Tip motorja" -> tipMotorja.getSelectionModel().select(value);
                            case "Cena" -> cena.setText(value);
                            case "Ime in priimek kartice" -> imePriimekKartica.setText(value);
                            case "Številka kartice" -> stevilkaKartice.setText(value);
                            case "Naslov" -> naslovNarocnik.setText(value);
                            case "Ime in priimek naročnika" -> imePriimekNarocnik.setText(value);
                            case "Dodatno varovanje" -> dodatnoVarovanje.setSelected(value.equals("Da"));
                            case "Elektronski naslov" -> emailNarocnik.setText(value);
                            case "Telefon" -> telefonNarocnik.setText(value);
                            case "Starost" -> starostNarocnik.getValueFactory().setValue(Integer.parseInt(value));
                            case "Število let izkušenj z vožnjo" -> vozniskoNarocnik.getValueFactory().setValue(Integer.parseInt(value));
                            case "CVV" -> cvv.setText(value);
                            case "Datum izteka" -> datumIzteka.setValue(LocalDate.parse(value));
                            default -> {
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void shraniCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Izberi datoteko za shranjevanje");
        File f = fc.showSaveDialog(null);
        String zapis = "";
        if (f != null){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                zapis = formatUserInput();
                bw.write(zapis);
                status.setTextFill(BLACK);
                status.setText("Shranjena datoteka: " + f.getName());
            } catch (Exception e){}
        }
    }

    public void izhodCB(ActionEvent actionEvent) {
        Image image = new Image("https://cdn.pixabay.com/photo/2016/01/20/18/35/x-1152114_1280.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        status.setText("Izhod.");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Izhod");
        alert.setHeaderText("Potrditev izhoda");
        alert.setContentText("Ali ste prepričani?");
        alert.setGraphic(imageView);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK)
                System.exit(0);
        });
    }

    public void ponastaviCB(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ponastavi");
        alert.setHeaderText("Ponastavljanje podatkov");
        alert.setContentText("Ali ste prepričani da želite ponastaviti podatke?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK){
                datumIzposoje.setValue(null);
                datumVrnitve.setValue(null);
                velicinaAvta.getSelectionModel().clearSelection();
                tipMenjalnika.getSelectionModel().clearSelection();
                casIzposoje1.getSelectionModel().clearSelection();
                casIzposoje2.getSelectionModel().clearSelection();
                casVrnitve1.getSelectionModel().clearSelection();
                casVrnitve2.getSelectionModel().clearSelection();
                avto.getSelectionModel().clearSelection();
                tipMotorja.getSelectionModel().clearSelection();
                cena.clear();
                imePriimekKartica.clear();
                stevilkaKartice.clear();
                naslovNarocnik.clear();
                imePriimekNarocnik.clear();
                dodatnoVarovanje.setSelected(false);
                emailNarocnik.clear();
                telefonNarocnik.clear();
                starostNarocnik.getValueFactory().setValue(0);
                vozniskoNarocnik.getValueFactory().setValue(0);
                cvv.clear();
                progress.setStyle("");
                progress.setProgress(0.0);
                datumIzteka.setValue(null);
                status.setText("Vsi podatki so ponastavljeni.");
            }
        });
    }


    public void pomocCB(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pomoč");
        alert.setHeaderText("Navodila");
        String pomoc = "1. Vnesite podatke o prevzemu in vračilu\n" +
                "2. Izberite možnosti in nastavitve avtomobila\n" +
                "3. Preverite izračunano ceno\n" +
                "3. Če je vse v redu, nadaljujte in vnesite podatke o naročniku in obračunu\n" +
                "4. Potrdite nakup\n" +
                "\n" +
                "Hvala in varno potovanje vam želimo!";
        alert.setContentText(pomoc);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK)
                alert.close();
        });
    }



    private String formatUserInput() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("*Podatki o izposoji*\n");
        stringBuilder.append("Datum izposoje: ").append(datumIzposoje.getValue()).append("\n");
        stringBuilder.append("Čas izposoje: ").append(casIzposoje1.getValue()).append(" : ").append(casIzposoje2.getValue()).append("\n");
        stringBuilder.append("Datum vrnitve: ").append(datumVrnitve.getValue()).append("\n");
        stringBuilder.append("Čas vrnitve: ").append(casVrnitve1.getValue()).append(" : ").append(casVrnitve2.getValue()).append("\n");
        stringBuilder.append("Velikost avta: ").append(velicinaAvta.getValue()).append("\n");
        stringBuilder.append("Tip menjalnika: ").append(tipMenjalnika.getValue()).append("\n");
        stringBuilder.append("Tip motorja: ").append(tipMotorja.getValue()).append("\n");
        stringBuilder.append("Avto: ").append(avto.getValue()).append("\n");
        stringBuilder.append("Dodatno varovanje: ").append(dodatnoVarovanje.isSelected() ? "Da" : "Ne").append("\n");
        stringBuilder.append("Cena: ").append(cena.getText()).append("\n\n");

        stringBuilder.append("*Podatki o naročniku*\n");
        stringBuilder.append("Ime in priimek naročnika: ").append(imePriimekNarocnik.getText()).append("\n");
        stringBuilder.append("Naslov: ").append(naslovNarocnik.getText()).append("\n");
        stringBuilder.append("Elektronski naslov: ").append(emailNarocnik.getText()).append("\n");
        stringBuilder.append("Telefon: ").append(telefonNarocnik.getText()).append("\n");
        stringBuilder.append("Starost: ").append(starostNarocnik.getValue()).append("\n");
        stringBuilder.append("Število let izkušenj z vožnjo: ").append(vozniskoNarocnik.getValue()).append("\n\n");

        stringBuilder.append("*Podatki o kartici*\n");
        stringBuilder.append("Ime in priimek kartice: ").append(imePriimekKartica.getText()).append("\n");
        stringBuilder.append("Številka kartice: ").append("xxxxxxxxxxxx").append(stevilkaKartice.getText().substring(12)).append("\n");
        stringBuilder.append("CVV: ").append("xxx").append("\n");
        stringBuilder.append("Datum izteka: ").append(datumIzteka.getValue()).append("\n");

        return stringBuilder.toString();
    }


    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean izpolnjenaVsaPolja() {
        boolean vsaPolja = true;

        if (datumIzposoje.getValue() == null ||
                datumVrnitve.getValue() == null ||
                casIzposoje1.getSelectionModel().isEmpty() ||
                casIzposoje2.getSelectionModel().isEmpty() ||
                casVrnitve1.getSelectionModel().isEmpty() ||
                casVrnitve2.getSelectionModel().isEmpty() ||
                velicinaAvta.getSelectionModel().isEmpty() ||
                tipMenjalnika.getSelectionModel().isEmpty() ||
                tipMotorja.getSelectionModel().isEmpty() ||
                avto.getSelectionModel().isEmpty() ||
                imePriimekNarocnik.getText().isEmpty() ||
                emailNarocnik.getText().isEmpty() ||
                telefonNarocnik.getText().isEmpty() ||
                starostNarocnik.getValue() == null ||
                vozniskoNarocnik.getValue() == null ||
                naslovNarocnik.getText().isEmpty() ||
                imePriimekKartica.getText().isEmpty() ||
                stevilkaKartice.getText().isEmpty() ||
                cvv.getText().isEmpty()) {
            vsaPolja = false;
        }

        return vsaPolja;
    }

    public void addListeners(){
        datumIzposoje.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        datumVrnitve.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        casIzposoje1.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        casIzposoje2.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        casVrnitve1.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        casVrnitve2.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        velicinaAvta.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        tipMenjalnika.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        tipMotorja.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        avto.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        imePriimekKartica.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        stevilkaKartice.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        naslovNarocnik.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        imePriimekNarocnik.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        emailNarocnik.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        telefonNarocnik.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        starostNarocnik.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        vozniskoNarocnik.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        cvv.textProperty().addListener((obs, oldVal, newVal) -> updateProgress());
        datumIzteka.valueProperty().addListener((obs, oldVal, newVal) -> updateProgress());
    }

    private void updateProgress() {
        double progress2 = calculateProgress();
        progress.setProgress(progress2);
        if (progress2 == 1.0){
            progress.setStyle("-fx-accent: green;");
        }
    }

    private double calculateProgress() {
        int completedFields = 0;

        if (datumIzposoje.getValue() != null) completedFields++;
        if (datumVrnitve.getValue() != null) completedFields++;
        if (velicinaAvta.getValue() != null) completedFields++;
        if (tipMenjalnika.getValue() != null) completedFields++;
        if (casIzposoje1.getValue() != null) completedFields++;
        if (casIzposoje2.getValue() != null) completedFields++;
        if (casVrnitve1.getValue() != null) completedFields++;
        if (casVrnitve2.getValue() != null) completedFields++;
        if (avto.getValue() != null) completedFields++;
        if (tipMotorja.getValue() != null) completedFields++;
        if (!imePriimekKartica.getText().isEmpty()) completedFields++;
        if (!stevilkaKartice.getText().isEmpty()) completedFields++;
        if (!naslovNarocnik.getText().isEmpty()) completedFields++;
        if (!imePriimekNarocnik.getText().isEmpty()) completedFields++;
        if (!emailNarocnik.getText().isEmpty()) completedFields++;
        if (!telefonNarocnik.getText().isEmpty()) completedFields++;
        if (starostNarocnik.getValue() != null) completedFields++;
        if (vozniskoNarocnik.getValue() != null) completedFields++;
        if (!cvv.getText().isEmpty()) completedFields++;
        if (datumIzteka.getValue() != null) completedFields++;

        double totalFields = 20;
        return completedFields / totalFields;
    }

    public void potrdiNarocilo(ActionEvent actionEvent) {
        if (izpolnjenaVsaPolja()) {
            if (!stevilkaKartice.getText().matches("[0-9]+")) {
                stevilkaKartice.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Kartica mora vsebovati samo številke.");
            } else if (!cvv.getText().matches("[0-9]+")) {
                cvv.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("CVV mora vsebovati samo številke.");
            } else if (!isValidEmailAddress(emailNarocnik.getText())){
                emailNarocnik.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Elektronski naslov ni v pravem obliku.");
            } else if (!imePriimekKartica.getText().matches("[\\p{L} '-]+ [\\p{L} '-]+")){
                imePriimekKartica.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Ime in priimek nista v pravem obliku.");
            } else if (!imePriimekNarocnik.getText().matches("[\\p{L} '-]+ [\\p{L} '-]+")){
                imePriimekNarocnik.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Ime in priimek nista v pravem obliku.");
            } else if (!naslovNarocnik.getText().matches("[\\p{L} '-]+")){
                naslovNarocnik.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Naslov ni v pravem obliku.");
            } else if (!telefonNarocnik.getText().matches("\\+?[0-9 -]+")) {
                telefonNarocnik.setStyle("-fx-background-color: #FFBDBD;");
                status.setTextFill(RED);
                status.setText("Telefon ni v pravem obliku.");
            } else {
                cvv.setStyle(null);
                naslovNarocnik.setStyle(null);
                imePriimekNarocnik.setStyle(null);
                emailNarocnik.setStyle(null);
                telefonNarocnik.setStyle(null);
                imePriimekKartica.setStyle(null);
                status.setTextFill(BLACK);
                status.setText("Potrditev naročila");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potrditev naročila");
                alert.setHeaderText("Podrobnosti naročila");
                alert.setContentText("*Podatki o izposoji*\n" +
                        "Datum izposoje: " + datumIzposoje.getValue() + "\n" +
                        "Cas izposoje: " + casIzposoje1.getValue() + ":" + casIzposoje2.getValue() + "\n" +
                        "Datum vrnitve: " + datumVrnitve.getValue() + "\n" +
                        "Cas vrnitve: " + casVrnitve1.getValue() + ":" + casVrnitve2.getValue() + "\n" +
                        "Velikost avta: " + velicinaAvta.getValue() + "\n" +
                        "Tip motorja: " + tipMotorja.getValue() + "\n" +
                        "Tip menjalnika: " + tipMenjalnika.getValue() + "\n" +
                        "Avto: " + avto.getValue() +  "\n" +
                        "Varovanje: " + (dodatnoVarovanje.isSelected() ? "Da" : "Ne") + "\n" +
                        "Cena: " + cena.getText() + "\n\n" +
                        "*Podatki o naročniku:\n" +
                        "Ime in priimek: " + imePriimekNarocnik.getText() + "\n" +
                        "Naslov: " + naslovNarocnik.getText() + "\n" +
                        "Telefon: " + telefonNarocnik.getText() + "\n" +
                        "Email: " + emailNarocnik.getText() + "\n" +
                        "Starost: " + starostNarocnik.getValue() + "\n" +
                        "Število let izkušenj z vožnjo: " + vozniskoNarocnik.getValue() + "\n\n" +
                        "*Podatki o kartici*\n" +
                        "Ime in priimek: " + imePriimekKartica.getText() + "\n" +
                        "Stevilka kartice: " + "xxxx-xxxx-xxxx-" + stevilkaKartice.getText().substring(12) + "\n" +
                        "CVV: " + "xxx" + "\n" +
                        "Datum izteka: " + datumIzteka.getValue() + "\n\n" +
                        "Ali ste prepričani da želite potrditi naročilo?");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Potrditev naročila");
                        alert1.setHeaderText("Naročilo je potrjeno");
                        alert1.setContentText("Hvala vam!");
                        alert1.showAndWait();
                    }
                });
            }
        } else {
            status.setTextFill(RED);
            status.setText("Prosimo, izpolnite vsa polja.");
            Alert validationAlert = new Alert(Alert.AlertType.ERROR);
            validationAlert.setTitle("Napaka!");
            validationAlert.setHeaderText(null);
            validationAlert.setContentText("Prosimo, izpolnite vsa polja.");
            validationAlert.showAndWait();
        }
    }


    public void prevediCB(ActionEvent actionEvent) {
        if (english)
            translateToEnglish();
        else translateToSlovenian();
        english = !english;
    }

    public void translateToEnglish(){
        odpriLabel.setText("Open");
        shraniLabel.setText("Save");
        poanstaviLabel.setText("Clear");
        pomocLabel.setText("Help");
        izhodLabel.setText("Exit");
        datumIzposojeLabel.setText("Rental date");
        datumVrnitveLabel.setText("Return date");
        casVrnitveLabel.setText("Return time");
        casIzposojeLabel.setText("Rental time");
        velicinaAvtaLabel.setText("Car size");
        tipMenjalnikaLabel.setText("Gearbox");
        tipMotorjaLabel.setText("Engine");
        avtoLabel.setText("Car");
        cenaLabel.setText("Price");
        kreditnaKarticaLabel.setText("Credit card info");
        stevilkaKarticeLabel.setText("Credit card number");
        datumIztekaLabel.setText("Expiry date");
        imePriimekKarticaLabel.setText("Name and surname");
        narocnikLabel.setText("Customer info");
        imePriimekNarocnikLabel.setText("Name and surname");
        starostLabel.setText("Age");
        vozniskoLabel.setText("Driver's license age");
        emailLabel.setText("Email");
        telefonLabel.setText("Phone number");
        potrdiNarociloLabel.setText("Confirm purchase");
        naslovNarocnikLabel.setText("Address");
        prevedi.setText("Translate");
        dodatnoVarovanje.setText("Additional guarantee (2€/day)");
    }

    public void translateToSlovenian(){
        odpriLabel.setText("Odpri");
        shraniLabel.setText("Shrani");
        poanstaviLabel.setText("Ponastavi");
        pomocLabel.setText("Pomoč");
        izhodLabel.setText("Izhod");
        datumIzposojeLabel.setText("Datum izposoje");
        datumVrnitveLabel.setText("Datum vrnitve");
        casVrnitveLabel.setText("Čas vrnitve");
        casIzposojeLabel.setText("Čas izposoje");
        velicinaAvtaLabel.setText("Veličina avta");
        tipMenjalnikaLabel.setText("Tip menjalnika");
        tipMotorjaLabel.setText("Tip motorja");
        avtoLabel.setText("Avto");
        cenaLabel.setText("Cena");
        kreditnaKarticaLabel.setText("Podatki o kreditni kartici");
        stevilkaKarticeLabel.setText("Številka kartice");
        datumIztekaLabel.setText("Datum izteka");
        imePriimekKarticaLabel.setText("Ime in priimek");
        narocnikLabel.setText("Podatki o naročniku");
        imePriimekNarocnikLabel.setText("Ime in priimek");
        starostLabel.setText("Starost");
        vozniskoLabel.setText("Starost vozniškega dovoljenja");
        emailLabel.setText("Elektronski naslov");
        telefonLabel.setText("Telefon");
        potrdiNarociloLabel.setText("Potrdi naročilo");
        naslovNarocnikLabel.setText("Naslov");
        prevedi.setText("Prevedi");
        dodatnoVarovanje.setText("Dodatno varovanje (2€/dan)");
    }
}