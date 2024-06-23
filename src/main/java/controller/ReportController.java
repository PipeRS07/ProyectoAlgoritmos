package controller;

import Data.PdfData;
import domain.clasesBase.*;
import domain.graph.GraphException;
import domain.list.ListException;
import domain.queue.QueueException;
import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import util.Ruta;
import util.Utility;

import static util.Utility.Bitacora;

public class ReportController {
    @javafx.fxml.FXML
    private BorderPane bp;

    @javafx.fxml.FXML
    public void generateStudentProgressReport(ActionEvent actionEvent) throws TreeException, ListException, GraphException, QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha generado un reporte de Progreso");

        String result = "";
        Curso c;
        Estudiante e;
        int n =0;
        for (int i = 0; i < Utility.cursosRegistrados.size(); i++) {
            c=((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data);
            result+= c.getInscripcionesEstudiantes().toString();
//            n= ((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data).getInscripcionesEstudiantes().size();
//            c=((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data);
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k <Utility.usuariosRegistrados.size(); k++) {
//                    if(((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data).getInscripcionesEstudiantes().containsEdge(c, (User)Utility.usuariosRegistrados.getNode(k).data)){
//                        System.out.println();
//                    }
//
//                }
//
//            }
        }
        PdfData pdf = new PdfData("informe");

        pdf.generarYEnviarPDF((new Reporte("Informe de inscripciones por curso", result, Utility.UserActivo.getEmail())));

    }

    @javafx.fxml.FXML
    public void generateEvaluationReport(ActionEvent actionEvent) throws ListException, TreeException, QueueException {
        Bitacora.enQueue(Utility.UserActivo.getName() + "Se ha generado un reporte de Evaluaciones");

        String result = "";
        Curso c;
        Estudiante e;

        for (int i = 0; i < Utility.cursosRegistrados.size(); i++) {
            c=((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data);
            result+= c.getEvaluaciones().toString();
//
        }
        PdfData pdf = new PdfData("informe");

        pdf.generarYEnviarPDF((new  Reporte("Informe de evaluaciones y calificaciones", result, Utility.UserActivo.getEmail())));


    }

    @javafx.fxml.FXML
    public void generateCourseEnrollmentReport(ActionEvent actionEvent) throws ListException, TreeException {
        String result = "";
        Curso c;
        Estudiante e;

        for (int i = 0; i < Utility.cursosRegistrados.size(); i++) {
            c=((Curso)Utility.cursosRegistrados.getElementAtPosition(i).data);
            result+= c.getEvaluaciones().toString();
//
        }
        PdfData pdf = new PdfData("informe");
        pdf.generarYEnviarPDF((new  Reporte("Informe del progreso de cada estudiante:", result, Utility.UserActivo.getEmail())));

    }
}
