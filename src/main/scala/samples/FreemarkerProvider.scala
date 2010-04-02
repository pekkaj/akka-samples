/*
package samples

import com.sun.jersey.spi.template.TemplateProcessor;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io._
import java.net.MalformedURLException;
import java.util._

@Provider
class FreemarkerTemplateProvider extends TemplateProcessor {

    private static final Logger log = Logger.getLogger( FreemarkerTemplateProvider.class );

    private static Configuration freemarkerConfig;

    private String rootPath;
    private ServletContext servletContext;

    public FreemarkerTemplateProvider() {}

    protected String getDefaultExtension()
    {
        return ".ftl";
    }


    @SuppressWarnings( {"UnusedDeclaration"} )
    protected Map<String,Object> getVariablesForTemplate(
            final Map<String,Object> viewableVariables )
    {
        return new HashMap<String,Object>();
    }

    @SuppressWarnings( {"UnusedDeclaration"} )
    protected void onProcessException( final Throwable t, final String templatePath,
                                       final Map<String,Object> templateContext,
                                       final OutputStream out )
            throws IOException
    {
        log.error( "Error processing freemarker template @ " + templatePath + ": " + t.getMessage(), t );
        out.write( "<pre>".getBytes() );
        t.printStackTrace( new PrintStream( out ) );
        out.write( "</pre>".getBytes() );
    }

    @SuppressWarnings( {"UnusedDeclaration"} )
    protected void assignFreemarkerConfig( final Configuration config,
                                           final ServletContext context )
    {
        // don't always put a ',' in numbers (e.g., id=2000 vs id=2,000)
        config.setNumberFormat( "0" );

        // don't look for list.en.ftl when list.ftl requested
        config.setLocalizedLookup( false );

        // don't cache
        config.setTemplateUpdateDelay(0);

        log.info( "OK: Assigned default freemarker configuration" );

    }

    public String resolve( final String path )
    {
        if ( log.isDebugEnabled() )
            log.debug( "Resolving freemarker template path (" + path + ")" );

        // accept both '/path/to/template' and '/path/to/template.ftl'
        final String defaultExtension = getDefaultExtension();
        final String filePath = path.endsWith( defaultExtension ) ? path : path + defaultExtension;
        try {
            final String fullPath = rootPath + filePath;
            final boolean templateFound = servletContext.getResource( fullPath ) != null;
            if ( ! templateFound )
                log.warn( "Template not found [Given path: " + path + "] " +
                          "[Servlet context path: " + fullPath + "]" );
            return templateFound ? filePath : null;
        }
        catch ( MalformedURLException e ) {
            log.warn( "Caught MalformedURLException when trying to get freemarker resource (" + filePath + ") " +
                      "from the servlet context: " + e.getMessage() );
            return null;
        }
    }

    @SuppressWarnings( { "unchecked" } )
    public void writeTo( final String resolvedPath, final Object model,
                         final OutputStream out ) 
            throws IOException
    {
        if ( log.isDebugEnabled() )
            log.debug( "Evaluating freemarker template (" + resolvedPath + ") with model of type " +
                       ( model == null ? "null" : model.getClass().getSimpleName() ) );

        out.flush(); // send status + headers

        final Template template = freemarkerConfig.getTemplate( resolvedPath );
        if ( log.isDebugEnabled() )
            log.debug( "OK: Resolved freemarker template" );

        final Map<String,Object> vars = new HashMap<String, Object>();

        if ( model instanceof Map ) {
            vars.putAll( (Map<String, Object>)model );
        }
        else {
            vars.put( "it", model );
        }

        final Map<String,Object> extraVars = getVariablesForTemplate( new HashMap<String, Object>( vars ) );
        if ( extraVars != null ) {
            vars.putAll( extraVars );
        }
        
        final OutputStreamWriter writer = new OutputStreamWriter( out );

        try {
            template.process( vars, writer );
            if ( log.isDebugEnabled() )
                log.debug( "OK: Processed freemarker template" );
        }
        catch ( Throwable t ) {
            onProcessException( t, resolvedPath, vars, out );
        }
    }

    @Context
    public void setServletContext( final ServletContext context )
    {
        this.servletContext = context;

        freemarkerConfig = new Configuration();

        rootPath = context.getInitParameter( "freemarker.template.path" );
        if ( rootPath == null || rootPath.trim().length() == 0 ) {
            log.info( "No 'freemarker.template.path' context-param, " +
                      "defaulting to '/WEB-INF/templates'" );
            rootPath = "/WEB-INF/templates";
        }
        rootPath = rootPath.replaceAll( "/$", "" );

        freemarkerConfig.setTemplateLoader( new WebappTemplateLoader( context, rootPath ) );

        final InputStream fmProps = context.getResourceAsStream( "freemarker.properties" );
        if ( fmProps != null ) {
            try {
                freemarkerConfig.setSettings( fmProps );
                log.info( "OK: Assigned freemarker configuration from 'freemarker.properties'" );
                return;
            }
            catch ( Throwable t ) {
                log.warn( "Failed to load/assign freemarker.properties, will use default settings " +
                          "instead; error: " + t.getMessage() );
            }
        }
        assignFreemarkerConfig( freemarkerConfig, context );
    }
}
*/
