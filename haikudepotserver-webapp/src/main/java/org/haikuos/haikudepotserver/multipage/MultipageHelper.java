/*
 * Copyright 2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.multipage;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.apache.cayenne.ObjectContext;
import org.haikuos.haikudepotserver.dataobjects.NaturalLanguage;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * <p>Helper (static) methods for the multipage part of the system.</p>
 */

public class MultipageHelper {

    /**
     * <p>This will look at parameters on the supplied request and will return a natural language.  It will
     * resort to English language if no other language is able to be derived.</p>
     */

    public static NaturalLanguage deriveNaturalLanguage(ObjectContext context, HttpServletRequest request) {
        Preconditions.checkNotNull(context);

        if(null!=request) {
            String naturalLanguageCode = request.getParameter(MultipageConstants.KEY_NATURALLANGUAGECODE);

            if(!Strings.isNullOrEmpty(naturalLanguageCode)) {
                Optional<NaturalLanguage> naturalLanguageOptional = NaturalLanguage.getByCode(context, naturalLanguageCode);

                if(!naturalLanguageOptional.isPresent()) {
                    throw new IllegalStateException("the natural language for code " + naturalLanguageCode + " was not able to be found");
                }

                return naturalLanguageOptional.get();
            }

            // see if we can deduce it from the locale.

            Locale locale = request.getLocale();

            if(null != locale) {
                Iterator<String> langI = Splitter.on(Pattern.compile("[-_]")).split(locale.toLanguageTag()).iterator();

                if(langI.hasNext()) {
                    Optional<NaturalLanguage> naturalLanguageOptional = NaturalLanguage.getByCode(context, langI.next());

                    if(naturalLanguageOptional.isPresent()) {
                        return naturalLanguageOptional.get();
                    }
                }

            }
        }

        return NaturalLanguage.getByCode(context, NaturalLanguage.CODE_ENGLISH).get();
    }

}