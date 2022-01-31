package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Scenario utils
 * 
 * @author jabier.martinez
 */
public class ScenarioUtils {

	/**
	 * Get all scenarios
	 * 
	 * @param scenariosFolder
	 * @return list of scenarios
	 */
	public static List<File> getAllScenarios(File scenariosFolder) {
		List<File> scenarios = new ArrayList<File>();
		// Get all the files inside the scenario Folder
		File[] scenarioFile = scenariosFolder.listFiles();

		// Check if the array of Files is not null
		if (scenarioFile != null) {
			for (File scenarioDirectory : scenarioFile) {
				// Check if the scenarioDirectory is a folder
				if (scenarioDirectory.isDirectory()) {
					scenarios.add(scenarioDirectory);
				}
			}
		}
		return scenarios;
	}

	/**
	 * Get all scenarios ordered by number of variants
	 * 
	 * @param scenariosFolder
	 * @return list of scenarios
	 */
	public static List<File> getAllScenariosOrderedByNumberOfVariants(File scenariosFolder) {
		List<File> scenarios = getAllScenarios(scenariosFolder);

		Collections.sort(scenarios, new Comparator<File>() {
			@Override
			public int compare(File f1, File f2) {
				return getNumberOfVariantsInScenario(f1) - getNumberOfVariantsInScenario(f2);
			}
		});
		return scenarios;
	}

	/**
	 * Get number of variants in scenario
	 * @param scenarioFolder
	 * @return number of variants
	 */
	public static int getNumberOfVariantsInScenario(File scenarioFolder) {
		File configs = new File(scenarioFolder, "configs");
		return configs.listFiles().length;
	}
	
	/**
	 * Is the scenario built
	 * @param scenarioFolder
	 * @return true if variants were derived
	 */
	public static boolean isScenarioBuilt(File scenarioFolder) {
		File variants = new File(scenarioFolder, "variants");
		return variants.exists();
	}

}
