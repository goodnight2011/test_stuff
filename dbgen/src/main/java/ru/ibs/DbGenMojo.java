package ru.ibs;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo( name = "dbgen")
public class DbGenMojo extends AbstractMojo{
    public void execute() throws MojoExecutionException
    {
        getLog().info( "Hello, world." );
    }
}
