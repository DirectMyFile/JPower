package jpower.groovy

import jpower.core.Wrapper

class JPowerExtension {
    static boolean asBoolean(Wrapper<?> wrapper) {
        return wrapper.notNull && wrapper.get().asBoolean()
    }
}
