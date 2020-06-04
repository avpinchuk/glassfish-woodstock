/*
 * Copyright (c) 2007, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package com.sun.webui.jsf.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import com.sun.webui.jsf.component.FileChooser;

// This is difficult to get right. We assume the
// Object is a String. But it is the converted value of
// a possible developer defined facet. If we require the object
// to be a native type of the FileChooserModel we should be ok.
//
/**
 * There has to be validator on the LookIn component because it
 * may be a developer defined facet. This is the only place where
 * the FileChooser policy can be enforced in order to not
 * have the local value set to an invalid value.
 *
 * Since all validators are given a chance to validate even if
 * one fails, other validators should not attempt to validate
 * if the component is invalid at the time a validator is called.
 *
 * This validator, if it determines the value invalid, will clear
 * the submitted value in order to ensure the last known
 * valid value is rendered.
 */
// Note that typing this by referencing FileChooser
// prevents using this in a general Chooser paradigm.
//
public final class FileChooserLookInValidator implements Validator {

    @Override
    public void validate(final FacesContext context,
            final UIComponent component, final Object value)
            throws ValidatorException {

        FileChooser chooser = (FileChooser) component.getParent();
        chooser.validateLookInComponent(context, component, value);
    }
}
