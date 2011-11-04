def target = System.properties['env.target']
def ant = new AntBuilder()

ant.echo("Target Environment: ${target}")

switch(target) {
    case 'test':
        ant.echo('test')
        // Ant tasks for Test env
        break
    case 'prod':
        ant.echo('Prod')
        // Ant tasks for Test env
        break
    default:
        ant.echo('target missing: -Denv.target=test|prod')
}
