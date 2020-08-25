import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD011InstalationChauffageDetailComponent } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage-detail.component';
import { TD011InstalationChauffage } from 'app/shared/model/td-011-instalation-chauffage.model';

describe('Component Tests', () => {
  describe('TD011InstalationChauffage Management Detail Component', () => {
    let comp: TD011InstalationChauffageDetailComponent;
    let fixture: ComponentFixture<TD011InstalationChauffageDetailComponent>;
    const route = ({ data: of({ tD011InstalationChauffage: new TD011InstalationChauffage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD011InstalationChauffageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD011InstalationChauffageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD011InstalationChauffageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD011InstalationChauffage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD011InstalationChauffage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
