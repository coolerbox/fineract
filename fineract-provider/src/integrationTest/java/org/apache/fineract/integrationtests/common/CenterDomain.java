/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.integrationtests.common;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.fineract.infrastructure.core.service.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CenterDomain implements Comparable<CenterDomain> {
    private final static Logger LOG = LoggerFactory.getLogger(CenterDomain.class);
    public static class Builder {

        private Integer id;
        private String accountNo;
        private HashMap<String, Object> status;
        private boolean active;
        private String name;
        private String externalId;
        private Integer staffId;
        private Integer officeId;
        private String officeName;
        private String hierarchy;
        private ArrayList<HashMap> groupMembers;

        private Builder(final Integer id, final Integer statusid, final String statuscode, final String statusvalue, final boolean active,
                final String name, final String externalId, final Integer staffId, final int officeID, final String officeName,
                final String hierarchy, final ArrayList<HashMap> groupMembers) {
            this.id = id;
            this.status = new HashMap<String, Object>();
            this.status.put("id", statusid);
            this.status.put("code", statuscode);
            this.status.put("value", statusvalue);
            this.active = active;
            this.name = name;
            this.externalId = externalId;
            this.staffId = staffId;
            this.officeId = officeID;
            this.officeName = officeName;
            this.hierarchy = hierarchy;
            this.groupMembers = groupMembers;
        }

        public CenterDomain build() {
            return new CenterDomain(this.id, this.accountNo, (int) this.status.get("id"), (String) this.status.get("code"),
                    (String) this.status.get("value"), this.active, this.name, this.externalId, this.staffId, this.officeId,
                    this.officeName, this.hierarchy, groupMembers);
        }

    }

    private Integer id;
    private String accountNo;
    private HashMap<String, Object> status;
    private boolean active;
    private String name;
    private String externalId;
    private Integer staffId;
    private Integer officeId;
    private String officeName;
    private String hierarchy;
    private ArrayList<HashMap> groupMembers;

    CenterDomain() {
        /* super(); */
    }

    private CenterDomain(final Integer id, final String accountNo, final Integer statusid, final String statuscode, final String statusvalue, final boolean active,
            final String name, final String externalId, final Integer staffId, final Integer officeID, final String officeName,
            final String hierarchy, final ArrayList<HashMap> groupMembers) {
        this.id = id;
        this.accountNo = accountNo;
        this.status = new HashMap<String, Object>();
        this.status.put("id", statusid);
        this.status.put("code", statuscode);
        this.status.put("value", statusvalue);
        this.active = active;
        this.name = name;
        this.externalId = externalId;
        this.staffId = staffId;
        this.officeId = officeID;
        this.officeName = officeName;
        this.hierarchy = hierarchy;
        this.groupMembers = groupMembers;
    }

    public String toJSON() {
        return new Gson().toJson(this);
    }

    public static CurrencyDomain fromJSON(final String jsonData) {
        return new Gson().fromJson(jsonData, CurrencyDomain.class);
    }

    public static Builder create(final Integer id, final Integer statusid, final String statuscode, final String statusvalue,
            final boolean active, final String name, final String externalId, final Integer staffId, final Integer officeID,
            final String officeName, final String hierarchy, final ArrayList<HashMap> groupMembers) {
        return new Builder(id, statusid, statuscode, statusvalue, active, name, externalId, staffId, officeID, officeName, hierarchy,
                groupMembers);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String jsonRequestToCreateCenter(Integer id, Integer statusId, String statusCode, String statusValue, Boolean active,
            String activationDate, String submittedDate, String name, String externalId, Integer staffId, Integer officeID,
            String officeName, String hierarchy, final int[] groupMembers) {
        // String ids = String.valueOf(id);
        final HashMap map = new HashMap<>();
        if (id != null) map.put("id", id);
        if (statusId != null) map.put("statusId", statusId);
        if (statusCode != null) map.put("statusCode", statusCode);
        if (statusValue != null) map.put("statusValue", statusValue);
        map.put("officeId", "1");
        map.put("name", randomNameGenerator("Center_Name_", 5));
        map.put("externalId", randomIDGenerator("ID_", 7));
        map.put("dateFormat", "dd MMMM yyyy");
        map.put("locale", "en");
        if (staffId != null) {
            map.put("staffId", String.valueOf(staffId));
        }
        if (active) {
            map.put("active", "true");
            map.put("locale", "en");
            map.put("dateFormat", "dd MMM yyyy");
            map.put("activationDate", activationDate);
        } else {
            map.put("active", "false");
            if (submittedDate == null)
                map.put("submittedOnDate", DateUtils.getDateOfTenant());
            else
                map.put("submittedOnDate", submittedDate);
        }
        if (externalId != null) map.put("externalId", externalId);
        if (groupMembers != null) map.put("groupMembers", groupMembers);
        LOG.info("{}",map.toString());
        return new Gson().toJson(map);
    }

    public static String randomNameGenerator(final String prefix, final int lenOfRandomSuffix) {
        return Utils.randomStringGenerator(prefix, lenOfRandomSuffix);
    }

    private static String randomIDGenerator(final String prefix, final int lenOfRandomSuffix) {
        return Utils.randomStringGenerator(prefix, lenOfRandomSuffix, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }

    public String getExternalId() {
        return this.externalId;
    }

    public Integer getStaffId() {
        return this.staffId;
    }

    public Integer getId() {
        return this.id;
    }

    public HashMap getStatus() {
        return this.status;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getName() {
        return this.name;
    }

    public Integer getOfficeId() {
        return this.officeId;
    }

    public String getOfficeName() {
        return this.officeName;
    }

    public String getHierarchy() {
        return this.hierarchy;
    }

    public String getAccountNo(){
        return this.accountNo;
    }

    public int[] getGroupMembers() {
        int[] groupMemberList = new int[this.groupMembers.size()];
        for (int i = 0; i < groupMemberList.length; i++) {
            groupMemberList[i] = ((Double) this.groupMembers.get(i).get("id")).intValue();
        }
        return groupMemberList;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        if (this.id >= 0) hash += this.id;
        if (this.status != null) {
            if ((Double) this.status.get("id") >= 0) hash += (Double) this.status.get("id");
            if ((String) this.status.get("code") != null) hash += this.status.get("code").hashCode();
            if ((String) this.status.get("value") != null) hash += this.status.get("value").hashCode();
        }
        if (this.name != null) hash += this.name.hashCode();
        if (this.officeId >= 0) hash += this.officeId;
        if (this.officeName != null) hash += this.officeName.hashCode();
        if (this.hierarchy != null) hash += this.hierarchy.hashCode();
        if (this.groupMembers != null) hash += this.groupMembers.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }

        if (!(obj instanceof CenterDomain)) return false;

        CenterDomain cd = (CenterDomain) obj;

        if (this.hashCode() == cd.hashCode()) return true;
        return false;
    }

    @Override
    public int compareTo(CenterDomain cd) {
        return ((Integer) this.id).compareTo(cd.getId());
    }
}
