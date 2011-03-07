package org.eclipse.emf.henshin.statespace.validation;

/**
 * Data class for XY-plots. Can be used as validation results.
 * @author Christian Krause
 */
public class StateSpaceXYPlot {
	
	// Name of the X- and Y-axis.
	private String xName, yName;
	
	// X- and Y-coordinates of the plots.
	private double[][] xValues, yValues;
	
	// Minimum and maximum values.
	private double xMin = 0, yMin = 0, xMax = 0, yMax = 0;
	
	// Maximum number of segments.
	private int xMaxSegments = 0, yMaxSegments = 0;
	
	/**
	 * Default constructor.
	 * @param xName Name of the X-axis.
	 * @param yName Name of the Y-axis.
	 * @param xValues X-values.
	 * @param yValues Y-values.
	 */
	public StateSpaceXYPlot(String xName, String yName, double[][] xValues, double[][] yValues) {
		
		this.xName = xName;
		this.yName = yName;
		this.xValues = xValues;
		this.yValues = yValues;
		
		// Compute minimum and maximum values.
		for (int series=0; series<getSeriesCount(); series++) {
			for (int point=0; point<getSeriesLength(series); point++) {
				double x = xValues[series][point];
				double y = yValues[series][point];
				xMin = Math.min(xMin,x);
				yMin = Math.min(yMin,y);
				xMax = Math.max(xMax,x);
				yMax = Math.max(yMax,y);
				xMaxSegments = Math.max(xMaxSegments, xValues[series].length);
				yMaxSegments = Math.max(yMaxSegments, yValues[series].length);
			}
		}
		
	}
	
	public int getSeriesCount() {
		return Math.min(xValues.length, yValues.length);
	}
	
	public int getSeriesLength(int series) {
		return Math.min(xValues[series].length, yValues[series].length);
	}

	public double getX(int series, int point) {
		return xValues[series][point];
	}

	public double getY(int series, int point) {
		return yValues[series][point];
	}

	public int getXScaled(int series, int point, int span) {
		double value = xValues[series][point];
		return (int) ((double)span * value/xMax);
	}

	public int getYScaled(int series, int point, int span) {
		double value = yValues[series][point];
		return (int) ((double)span * value/yMax);
	}

	public double getXMin() {
		return xMin;
	}

	public double getYMin() {
		return yMin;
	}

	public double getXMax() {
		return xMax;
	}

	public double getYMax() {
		return yMax;
	}

	public int getXMaxSegments() {
		return xMaxSegments;
	}

	public int getYMaxSegments() {
		return yMaxSegments;
	}

	public String getXName() {
		return xName;
	}
	
	public String getYName() {
		return yName;
	}

}
