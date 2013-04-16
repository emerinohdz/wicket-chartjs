package com.pingunaut.wicket.chartjs.core;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.model.IModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pingunaut.wicket.chartjs.chart.ISimpleChart;

public class SimpleChartPanel<C extends ISimpleChart> extends AbstractChartPanel<C> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2899070514574123978L;

	public SimpleChartPanel(String id, IModel<C> c) {
		super(id, c);
	}

	public SimpleChartPanel(String id, IModel<C> c, int width, int height) {
		super(id, c, width, height);
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		String dataString = null;
		try {
			dataString = getChart().getMapper().writeValueAsString(getChart().getData());
			System.out.println(dataString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.render(OnDomReadyHeaderItem.forScript("WicketCharts['" + getChartCanvas().getMarkupId() + "']." + getChart().getClass().getSimpleName() + "(" + dataString + ", "
				+ getChart().getOptions() + ");"));
	}
}