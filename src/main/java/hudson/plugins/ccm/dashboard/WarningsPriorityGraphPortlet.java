package hudson.plugins.ccm.dashboard;

import hudson.Extension;
import hudson.model.Descriptor;
import hudson.plugins.analysis.core.AbstractProjectAction;
import hudson.plugins.analysis.dashboard.AbstractWarningsGraphPortlet;
import hudson.plugins.analysis.graph.BuildResultGraph;
import hudson.plugins.analysis.graph.PriorityGraph;
import hudson.plugins.ccm.CcmProjectAction;
import hudson.plugins.ccm.Messages;
import hudson.plugins.view.dashboard.DashboardPortlet;

import org.kohsuke.stapler.DataBoundConstructor;

/**
 * A portlet that shows the warnings trend graph by priority.
 *
 * @author Ulli Hafner
 * @since 3.0
 */
public final class WarningsPriorityGraphPortlet extends AbstractWarningsGraphPortlet {
    /**
     * Creates a new instance of {@link WarningsPriorityGraphPortlet}.
     *
     * @param name
     *            the name of the portlet
     * @param width
     *            width of the graph
     * @param height
     *            height of the graph
     * @param dayCountString
     *            number of days to consider
     */
    @DataBoundConstructor
    public WarningsPriorityGraphPortlet(final String name, final String width, final String height, final String dayCountString) {
        super(name, width, height, dayCountString);

        configureGraph(getGraphType());
    }

    /** {@inheritDoc} */
    @Override
    protected Class<? extends AbstractProjectAction<?>> getAction() {
        return CcmProjectAction.class;
    }

    /** {@inheritDoc} */
    @Override
    protected String getPluginName() {
        return "pmd";
    }

    /** {@inheritDoc} */
    @Override
    protected BuildResultGraph getGraphType() {
        return new PriorityGraph();
    }

    /**
     * Extension point registration.
     *
     * @author Ulli Hafner
     */
    @Extension(optional = true)
    public static class WarningsGraphDescriptor extends Descriptor<DashboardPortlet> {
        @Override
        public String getDisplayName() {
            return Messages.CCM_Portlet_WarningsPriorityGraph();
        }
    }
}

