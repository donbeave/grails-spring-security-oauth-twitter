/*
 * Copyright 2012 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import grails.util.Environment

/**
 * @author <a href='mailto:cazacugmihai@gmail.com'>Mihai Cazacu</a>
 * @author <a href='mailto:enrico@comiti.name'>Enrico Comiti</a>
 * @author <a href='mailto:donbeave@gmail.com'>Alexey Zhokhov</a>
 */
class SpringSecurityOauthTwitterGrailsPlugin {

    def version = '0.2'
    def grailsVersion = '2.0 > *'

    def loadAfter = ['springSecurityOauth']

    def pluginExcludes = [
            'grails-app/views/error.gsp'
    ]

    def title = 'Twitter for Spring Security OAuth plugin'
    def author = 'Mihai Cazacu, Enrico Comiti, Alexey Zhokhov'
    def authorEmail = 'donbeave@gmail.com'
    def description = '''\
Integrate [Twitter|http://www.twitter.com] to [Spring Security OAuth plugin|http://grails.org/plugin/spring-security-oauth].
'''

    def documentation = 'https://github.com/donbeave/grails-spring-security-oauth-twitter'

    def license = 'APACHE'

    def developers = [
            [name: 'Mihai Cazacu', email: 'cazacugmihai@gmail.com'],
            [name: 'Enrico Comiti', email: 'enrico@comiti.name'],
            [name: 'Alexey Zhokhov', email: 'donbeave@gmail.com']]
    def organization = [name: 'Polusharie', url: 'http://www.polusharie.com']

    def issueManagement = [system: 'GITHUB',
                           url   : 'https://github.com/donbeave/grails-spring-security-oauth-twitter/issues']
    def scm = [url: 'https://github.com/donbeave/grails-spring-security-oauth-twitter']

    def doWithSpring = {
        loadConfig(application.config)
    }

    private void loadConfig(ConfigObject config) {
        def classLoader = new GroovyClassLoader(getClass().classLoader)

        // Note here the order of objects when calling merge - merge OVERWRITES values in the target object
        // Load default config as a basis
        def newConfig = new ConfigSlurper(Environment.current.name).parse(
                classLoader.loadClass('DefaultTwitterOauthConfig')
        )

        newConfig.oauth.providers.twitter.merge(config.oauth.providers.twitter)

        // Now add DefaultTwitterOauthConfig into the main config
        config.merge(newConfig)
    }

}
