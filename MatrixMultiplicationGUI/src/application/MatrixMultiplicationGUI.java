package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MatrixMultiplicationGUI extends Application {
	TextField field;
	ArrayList<TextField> values = new ArrayList<>();
	int count = 1;
	char findPaths = 'n';

	public void start(Stage primarystage) {
		Scene input;
		primarystage.setTitle("Matrix Multiplication");

		// Input Scene
		FlowPane border = new FlowPane();
		border.setPadding(new Insets(11));
		border.setHgap(10);
		border.setVgap(10);

		// Input row 
		Label inputDimensions = new Label("Please input row/column amount: ");
		inputDimensions.setFont(new Font("Times new Roman", 15));
		TextField inputDimensionsField = new TextField();
		inputDimensionsField.setText("2");

		// Input number of compositions 
		Label inputComposition = new Label("Please input composition amount: ");
		inputComposition.setFont(new Font("Times new Roman", 15));
		TextField compositionField = new TextField();
		compositionField.setText("2");

		// Checklist
		Label pathChecklist = new Label("Do you want to find number of paths? ");
		pathChecklist.setFont(new Font("Times new Roman", 15));
		CheckBox pathCheckField = new CheckBox();

		// Enter button
		Button initialEnter = new Button("Enter");

		EventHandler<ActionEvent> enterHandler = e -> {
			GridPane matrix = new GridPane();
			BorderPane pane = new BorderPane();
			Scene display = new Scene(pane, 180 + 15*Integer.parseInt(inputDimensionsField.getText()), 50 + 25*Integer.parseInt(inputDimensionsField.getText()));

			Label inputMatrix = new Label("Please input the matrix: ");
			inputMatrix.setFont(new Font("Times new Roman", 20));
			Button matrixEnter = new Button("Enter");

			primarystage.setScene(display);
			primarystage.show();

			// When enter is pressed
			EventHandler<ActionEvent> matrixEnterHandler = i1 -> {
				int[][] matrix1 = new int[Integer.parseInt(inputDimensionsField.getText())][Integer.parseInt(inputDimensionsField.getText())];
				int[][] finalMatrix = new int[Integer.parseInt(inputDimensionsField.getText())][Integer
				                                                                                .parseInt(inputDimensionsField.getText())]; // Last matrix composed
				int[][] finalSum = new int[Integer.parseInt(inputDimensionsField.getText())][Integer
				                                                                             .parseInt(inputDimensionsField.getText())]; // The sum of the composed matrices and the first matrix

				// Get values from initial matrix
				for (int i = 0; i < Integer.parseInt(inputDimensionsField.getText()) * Integer.parseInt(inputDimensionsField.getText());) {
					for (int row = 0; row < Integer.parseInt(inputDimensionsField.getText()); row++) {
						for (int col = 0; col < Integer.parseInt(inputDimensionsField.getText()); col++) {
							matrix1[row][col] = Integer.parseInt((values.get(i)).getText());
							i++;
						}	
					}
				}

				// Print original to console
				for(int i = 0; i < Integer.parseInt(inputDimensionsField.getText()); i++) {
					for(int j = 0; j < Integer.parseInt(inputDimensionsField.getText()); j++) {
						System.out.print(matrix1[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("-------------------------");

				// Transpose matrix due to how fields are generated in the grid panel
				int[][] initialMatrix = new int[Integer.parseInt(inputDimensionsField.getText())][Integer.parseInt(inputDimensionsField.getText())];
				for(int i = 0 ; i < Integer.parseInt(inputDimensionsField.getText()); i++) {
					for(int j = 0 ; j < Integer.parseInt(inputDimensionsField.getText()); j++) {
						initialMatrix[i][j] = matrix1[j][i];
					}
				}

				// Print transposed matrix to console
				for(int i = 0; i < Integer.parseInt(inputDimensionsField.getText()); i++) {
					for(int j = 0; j < Integer.parseInt(inputDimensionsField.getText()); j++) {
						System.out.print(initialMatrix[i][j] + " ");
					}
					System.out.println();
				}

				if(pathCheckField.isSelected()){
					findPaths = 'y';
				} 

				System.arraycopy(initialMatrix, 0, finalMatrix, 0, matrix1.length);

				pane.getChildren().clear();

				// Add number of labels to match the number of compositions
				Label transpositionLabel;
				FlowPane resultPane = new FlowPane(Orientation.VERTICAL);
				Scene resultsDisplay = new Scene(resultPane, 180 + 15*Integer.parseInt(inputDimensionsField.getText()), 50 + 25*Integer.parseInt(inputDimensionsField.getText()));

				primarystage.setScene(resultsDisplay);
				primarystage.show();
				for (int i = 1; i <= Integer.parseInt(compositionField.getText()); i++) {
					resultPane.getChildren().addAll((transpositionLabel = new Label("The value of R" + i + " is : ")));
					multiplyMatricesGUI(initialMatrix, finalMatrix, finalSum, Integer.parseInt(compositionField.getText()), count, findPaths);
				}





				multiplyMatrices(initialMatrix, finalMatrix, finalSum, Integer.parseInt(compositionField.getText()), count, findPaths);
			};

			// Add field to GridPane
			for (int i = 0; i < Integer.parseInt(inputDimensionsField.getText()); i++) {
				for (int j = 0; j < Integer.parseInt(inputDimensionsField.getText()); j++) {
					matrix.add(field = new TextField(), i, j);
					field.setText("0");
					values.add(field);
					field.setPrefWidth(30);
				}
			}


			matrixEnter.setOnAction(matrixEnterHandler);

			pane.setTop(inputMatrix);
			pane.setCenter(matrix);
			pane.setBottom(matrixEnter);
		};

		initialEnter.setOnAction(enterHandler);

		border.getChildren().addAll(inputDimensions, inputDimensionsField, inputComposition, compositionField, pathChecklist ,pathCheckField,
				initialEnter);
		border.setAlignment(Pos.BOTTOM_RIGHT);
		input = new Scene(border, 389, 155);

		primarystage.setScene(input);
		primarystage.show();
	}

	public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] finalMatrix, int[][] finalSum, int R, int count, char findPaths) {
		int r = firstMatrix[0].length;
		int c = firstMatrix.length;
		int[][] finalProduct = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++)
				finalProduct[i][j] = 0;
		}
		if (R < 1)
			return firstMatrix;
		else {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					for (int k = 0; k < c; k++) {
						finalProduct[i][j] += firstMatrix[i][k] * finalMatrix[k][j];
						finalSum[i][j] += finalMatrix[i][j] + finalProduct[i][j]; 
						// Calculates sum of compositions for checking transitivity
					}
					if(findPaths == 'n' | findPaths == 'N') {
						if (finalMatrix[i][j] > 1)
							finalMatrix[i][j] = 1;
						if (finalSum[i][j] > 1)
							finalSum[i][j] = 1;
					}
				}
			}

			System.out.println("The value of R" + count + " is : ");
			displayProduct(finalMatrix);
			return multiplyMatrices(firstMatrix, finalProduct, finalSum, R - 1, count + 1, findPaths); // Recursive call
		}
	}

	public static void displayProduct(int[][] product) {
		for (int[] row : product) {
			for (int column : row)
				System.out.print(column + "    ");
			System.out.println();
		}
		System.out.println();
	}

	public static int[][] multiplyMatricesGUI(int[][] firstMatrix, int[][] finalMatrix, int[][] finalSum, int R, int count, char findPaths) {
		int r = firstMatrix[0].length;
		int c = firstMatrix.length;
		int[][] finalProduct = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++)
				finalProduct[i][j] = 0;
		}
		if (R < 1)
			return firstMatrix;
		else {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					for (int k = 0; k < c; k++) {
						finalProduct[i][j] += firstMatrix[i][k] * finalMatrix[k][j];
						finalSum[i][j] += finalMatrix[i][j] + finalProduct[i][j]; 
						// Calculates sum of compositions for checking transitivity
					}
					if(findPaths == 'n' | findPaths == 'N') {
						if (finalMatrix[i][j] > 1)
							finalMatrix[i][j] = 1;
						if (finalSum[i][j] > 1)
							finalSum[i][j] = 1;
					}
				}
			}

			for (int[] row : finalMatrix) {
				for (int column : row) {
					Label resultsMatricesLabel = new Label(column + "    ");
//					resultPane.getChildren().addAll((transpositionLabel = new Label("The value of R" + i + " is : ")));
//					(resultsMatricesLabel).setText(column + "    ");
					//					System.out.print(column + "    ");
				}
			}
			return multiplyMatricesGUI(firstMatrix, finalProduct, finalSum, R - 1, count + 1, findPaths); // Recursive call
		}
	}

	public static void main(String[] args) {
		launch(args);

	}
}